package com.InvTierradentro.invtierradentro.Security;

import com.InvTierradentro.invtierradentro.aplication.Interfaces.IUserDao;
import com.InvTierradentro.invtierradentro.aplication.models.Dao.UserDao;
import com.InvTierradentro.invtierradentro.aplication.models.Dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@Component
@CrossOrigin(origins = "*")
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    IUserDao iUserDao;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication authentication) {
        Map<String,Object> info=new HashMap<>();
        UserDao userDao= iUserDao.findByUsername(authentication.getName());
        UserDTO userDTO=new UserDTO();
        userDTO.setUsername(userDao.getUsername());
        info.put("Informacion adicional",userDTO);

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
