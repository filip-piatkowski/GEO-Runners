
package com.infoshareacademy.pl.repository;

import com.infoshareacademy.pl.model.Track;

import java.io.IOException;
import java.util.List;

public interface TrackRepository {
    Track findTrackById(long trackId) throws IOException;
    List<Track> getAllTracks () throws IOException;
    void addTrack(Track trackToAdd) throws IOException;
    void removeTrackById (long trackId) throws IOException;

    long createRandomId();
}
