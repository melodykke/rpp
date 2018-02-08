package com.wrd.rpp.util;

import com.wrd.rpp.enums.UserAccontStatusEnum;
import com.wrd.rpp.form.UserRegistryForm;
import com.wrd.rpp.shiro.bean.UserInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;

public class UserUtil {
    public static UserInfo createDefaultUser(UserRegistryForm userRegistryForm){
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userRegistryForm, userInfo);
        userInfo.setState(UserAccontStatusEnum.ACCOUNT_INACTIVATED.getCode());
        String randomSalt = SaltUtil.getRandomSalt();
        String salt = randomSalt.concat(userRegistryForm.getUsername());
        userInfo.setSalt(salt);
        //MD5加盐两次散列 new Md5Hash(origin password, salt, times)
        userInfo.setPassword(new Md5Hash(userRegistryForm.getPassword(), salt, 2).toString());
        return userInfo;
    }
}
