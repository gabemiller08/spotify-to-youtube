package com.spotify.to.youtube.spotifytoyoutube.Service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

//import static com.spotify.to.youtube.spotifytoyoutube.Service.VideoPlaylistList.run;

public class SpotifyService {


    private static final String accessToken = "BQBYUyexieYLANP2P1fRcgkjEfE-V9K1wcFI-ooFfZL5rNFznDQ7wLEZup3JmPzDgayW3LskxQv9ce7C5yOT3OycLh1ZmspElnL6_VdSEduP37CndF0qS7a8KjWiF98_ezD5Qn-911c6HCtz63Yhr8FFlmv-soZtVQ";
    private static final String playlistId = "2aKUV2inxJtXb2Um4pUR57";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi
            .getPlaylistsTracks(playlistId)
//          .fields("description")
//          .limit(10)
//          .offset(0)
//          .market(CountryCode.SE)
            .build();

    public static void getPlaylistsTracks_Sync() {
        try {
            final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsTracksRequest.execute();

                    ArrayList<String> tracks = new ArrayList<String>();

                for(int i = 0; i < playlistTrackPaging.getItems().length; i++){
                    tracks.add(playlistTrackPaging.getItems()[i].getTrack().getName() + " - " + playlistTrackPaging.getItems()[i].getTrack().getArtists()[0].getName());
                    System.out.println(playlistTrackPaging.getItems()[i].getTrack().getName() + " - " + playlistTrackPaging.getItems()[i].getTrack().getArtists()[0].getName());
                }

           // run(tracks);






            //System.out.println("Total: " + playlistTrackPaging.getTotal());
            //ArrayList<String> playlistList = new ArrayList<>();
            //playlistTrackPaging.Builder.items() forEach((n) -> playlistList.add(n.getId().getVideoId()));
            //System.out.println("Total: " + playlistTrackPaging.getTotal());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getPlaylistsTracks_Async() {
        try {
            final CompletableFuture<Paging<PlaylistTrack>> pagingFuture = getPlaylistsTracksRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Paging<PlaylistTrack> playlistTrackPaging = pagingFuture.join();

            System.out.println("Total: " + playlistTrackPaging.getTotal());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }



    public static void main(String... args){
        // For all requests an access token is needed
//        SpotifyApi spotifyApi = new SpotifyApi.Builder()
//                .setAccessToken("taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk")
//                .build();

        getPlaylistsTracks_Sync();



    }
}
