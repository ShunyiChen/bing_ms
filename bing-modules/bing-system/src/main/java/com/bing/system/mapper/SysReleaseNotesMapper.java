package com.bing.system.mapper;

import com.bing.system.domain.ReleaseNote;

import java.util.List;

public interface SysReleaseNotesMapper {

    /**
     * 获取发布日期列表
     *
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
