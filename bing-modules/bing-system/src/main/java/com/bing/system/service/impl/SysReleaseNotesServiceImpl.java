package com.bing.system.service.impl;

import com.bing.system.domain.ReleaseNote;
import com.bing.system.mapper.SysReleaseNotesMapper;
import com.bing.system.service.ISysReleaseNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysReleaseNotesServiceImpl implements ISysReleaseNotesService {
    @Autowired
    private SysReleaseNotesMapper mapper;

    @Override
    public List<String> getReleaseDateList(ReleaseNote releaseNote) {
        return mapper.getReleaseDateList(releaseNote);
    }

    @Override
    public List<ReleaseNote> getReleaseNotesByDate(ReleaseNote releaseNote) {
        return mapper.getReleaseNotesByDate(releaseNote);
    }
}
