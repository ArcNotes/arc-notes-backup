package com.arcnotes.service;

import com.arcnotes.entity.Snapshot;
import com.arcnotes.entity.User;
import io.vavr.control.Option;

public interface SnapshotService extends BaseService<Snapshot> {

    Option<Snapshot> findByUserAndMd5(User user, String md5);

    Option<Snapshot> findLastSnapshotByUser(User user);
}
