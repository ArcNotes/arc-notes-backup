package com.arcnotes.controller.response;

import com.arcnotes.entity.Snapshot;
import com.arcnotes.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackupResponse {

    private Long userId;
    private String snapshotMd5;
    private LocalDateTime creationDate;

    public static BackupResponse from(Snapshot snapshot, User user) {
        return new BackupResponse(
                user.getId(),
                snapshot.getMd5(),
                snapshot.getCreationDate()
        );
    }
}
