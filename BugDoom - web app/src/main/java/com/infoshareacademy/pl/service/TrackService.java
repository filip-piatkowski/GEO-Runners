package com.infoshareacademy.pl.service;

import com.infoshareacademy.pl.exception.TrackNotFoundException;
import com.infoshareacademy.pl.model.Track;
import com.infoshareacademy.pl.repository.TrackRepository;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TrackService implements TrackRepository {

    private static final String TRACK_FILE_PATH = FilePathConstants.TRACK_FILE_PATH;
    private final DataService<Track> dataService = new DataService<>();

    public List<Track> getAllTracks() throws IOException {
        return new ArrayList<>(dataService.readFromFile(TRACK_FILE_PATH, Track[].class));
    }

    public void addTrack(Track trackToAdd) throws IOException {
        List<Track> allTracks = getAllTracks();
        allTracks.add(trackToAdd);
        saveTracksToFile(allTracks);
    }

    public void removeTrackById(long trackId) throws IOException {
        List<Track> allTracks = getAllTracks();
        Track trackToDelete = findTrackById(trackId);
        allTracks.remove(trackToDelete);
        saveTracksToFile(allTracks);
    }

    private void saveTracksToFile(List<Track> tracksToSave) throws IOException {
        dataService.saveToFile(tracksToSave, TRACK_FILE_PATH);
    }
@Override
    public Track findTrackById(long trackId) throws IOException{
        List<Track>allTracks = getAllTracks();
        return allTracks.stream().filter(track -> track.getTrackId() == trackId)
                .findFirst().orElseThrow(() -> new TrackNotFoundException("Nie ma trasy o takim ID: %s".formatted(trackId)));
    }
    public long createRandomId() {
        return new Random().nextLong(1001);
    }
}
