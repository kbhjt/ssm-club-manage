package com.nchu.club.vo;

import com.nchu.club.domain.Activity;
import com.nchu.club.domain.Club;

public class ClubActivityVo {

    private Activity activity;
    private Club club;

    public ClubActivityVo() {}

    public ClubActivityVo(Activity activity, Club club) {
        this.activity = activity;
        this.club = club;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
