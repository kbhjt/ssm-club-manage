package com.nchu.club.service;

import com.nchu.club.vo.ClubVo;

import java.util.List;

public interface ClubService {

    List<ClubVo> getAllClub(int page,int limit);
}
