package com.mikudd3.entity.request;

import com.mikudd3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private User user;
    private String checkCode;

    private Integer currentPage;

    private Integer pageSize;

}
