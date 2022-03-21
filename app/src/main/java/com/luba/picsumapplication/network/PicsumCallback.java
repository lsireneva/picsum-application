package com.luba.picsumapplication.network;

import com.luba.picsumapplication.models.Picture;

import java.util.ArrayList;

public interface PicsumCallback {

    void onSuccess(ArrayList<Picture> pictures);

    void onError(Error error);
}
