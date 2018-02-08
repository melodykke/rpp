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
        userInfo.setAccountStatus(UserAccontStatusEnum.ACCOUNT_INACTIVATED.getCode());
        userInfo.setAccountSalt(userRegistryForm.getAccountName());
        //MD5加盐两次散列 new Md5Hash(origin password, salt, times)
        userInfo.setAccountPassword(new Md5Hash(userRegistryForm.getAccountPassword(), userRegistryForm.getAccountName(), 2).toString());
        return userInfo;
    }
}
