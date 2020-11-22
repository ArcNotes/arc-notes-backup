package com.arcnotes.service;

import com.arcnotes.controller.request.BackupRequest;
import com.arcnotes.entity.Note;
import com.arcnotes.entity.Snapshot;
import com.arcnotes.entity.User;

import java.util.List;

public interface NoteService extends BaseService<Note> {

    Snapshot backup(BackupRequest request, User user);

    List<Note> restoreLast(User user);
}
