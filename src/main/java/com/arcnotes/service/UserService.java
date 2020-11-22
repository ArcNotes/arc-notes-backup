package com.arcnotes.service;

import com.arcnotes.entity.User;
import io.vavr.control.Option;

public interface UserService extends BaseService<User> {

    Option<User> findByNickname(String nickname);
}
