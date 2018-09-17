package at.emuhub.games.infos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EmuhubGamesInfosConfigurationAdapter {

    public Map<String, EmuhubGameInfo> adaptFrom(EmuhubGamesInfosConfiguration configuration) {
        Map<String, EmuhubGameInfo> gamesInfos = new HashMap<>();
        try {
            JSONArray gamesInfosJson = configuration.getJson().getJSONArray("gamesInfos");
            for (int i = 0; i < gamesInfosJson.length(); i++) {
                JSONObject gameInfoJson = gamesInfosJson.getJSONObject(i);
                String romName = gameInfoJson.getString("romName");
                gamesInfos.put(romName, new EmuhubGameInfo(gameInfoJson.getString("name"), romName));
            }
        } catch (JSONException e) {
            throw new IllegalStateException(e);
        }
        return gamesInfos;
    }
}