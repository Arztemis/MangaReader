package com.example.mangatoon;

import com.example.mangatoon.model.Action;
import com.example.mangatoon.model.Banner;
import com.example.mangatoon.model.Fiction;
import com.example.mangatoon.model.Horror;
import com.example.mangatoon.model.MaybeYouLiked;

import java.util.List;

public interface Callback {
    void getBannerUrlDone(List<Banner> list);

    void getMaybeYouLikeUrlDone(List<MaybeYouLiked> list);

    void getActionUrlDone(List<Action> list);

    void getHorrorUrlDone(List<Horror> list);

    void getFictionUrlDone(List<Fiction> list);

}
