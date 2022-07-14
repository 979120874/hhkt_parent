package com.ik.hhkt.feign;

import com.ik.hhkt.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-user")
public interface UserInfoFeignClient {

    @GetMapping("/admin/user/userInfo/inner/getById/{id}")
    public UserInfo getById(@PathVariable Long id);
}
