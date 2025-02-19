package com.bing.system.controller;

import com.bing.system.domain.ReleaseNote;
import com.bing.system.service.ISysReleaseNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/releasenotes")
public class SysReleaseNotesController {
    @Autowired
    private ISysReleaseNotesService service;

    @PostMapping("/getReleaseDateList")
    public List<String> getReleaseDateList(@RequestBody ReleaseNote releaseNote) {
        return service.getReleaseDateList(releaseNote);
    }

    /**
     * 获取参数配置列表
     */
    @PostMapping("/getReleaseNotesByDate")
    public Map<String, List<ReleaseNote>> getReleaseNotesByDate(@RequestBody ReleaseNote releaseNote) {
        List<ReleaseNote> releaseNotes = service.getReleaseNotesByDate(releaseNote);

        // 使用 HashMap 直接初始化分组
        Map<String, List<ReleaseNote>> groupedNotes = new HashMap<String, List<ReleaseNote>>() {{
            put("New Features", new ArrayList<>());
            put("Bug Fixes", new ArrayList<>());
            put("Improvements", new ArrayList<>());
            put("Additional Resources", new ArrayList<>());
        }};
        for (ReleaseNote note : releaseNotes) {
            groupedNotes.computeIfAbsent(note.getCategoryName(), k -> new ArrayList<>()).add(note);
        }
        return groupedNotes;
    }

}
