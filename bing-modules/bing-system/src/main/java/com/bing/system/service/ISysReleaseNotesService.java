package com.bing.system.service;

import com.bing.system.domain.ReleaseNote;

import java.util.List;

public interface ISysReleaseNotesService {
    /**
     * 获取发布日期列表
     *
     * @param releaseNote
     * @return
     */
    List<String> getReleaseDateList(ReleaseNote releaseNote);

    /**
     * 通过发布日期获取release notes
     *
     * @param releaseNote
     * @return
     */
    List<ReleaseNote> getReleaseNotesByDate(ReleaseNote releaseNote);
}
