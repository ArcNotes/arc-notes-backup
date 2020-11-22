package com.arcnotes.repository;

import com.arcnotes.entity.User;
import io.vavr.control.Option;

public interface UserRepository extends BaseRepository<User> {

    Option<User> findByNickname(String nickname);
}
