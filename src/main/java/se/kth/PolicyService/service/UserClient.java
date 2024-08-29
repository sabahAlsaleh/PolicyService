package se.kth.PolicyService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.kth.PolicyService.model.DTO.Role;
import se.kth.PolicyService.model.DTO.User;

@Service
public class UserClient {

    @Autowired
    private RestTemplate restTemplate;

    public User getUserById(Long userId) {
        String url = "http://localhost:8081/api/user/auth/" + userId;
        try {
            return restTemplate.getForObject(url, User.class);
        } catch (Exception e) {
            // Handle exception (e.g., log error, rethrow, return null)
            e.printStackTrace();
            return null;
        }

    }


    public Role getRoleById(Long roleId) {
        String url = "http://localhost:8081/api/roles/" + roleId;
        return restTemplate.getForObject(url, Role.class);
    }


}