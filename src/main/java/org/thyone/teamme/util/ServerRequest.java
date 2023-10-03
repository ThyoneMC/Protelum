package org.thyone.teamme.util;

import com.google.gson.Gson;
import org.jetbrains.annotations.Nullable;
import org.thyone.teamme.model.ServerResponse;
import org.thyone.teamme.model.ServerVerifyResponse;
import org.thyone.teamme.model.Team;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.UUID;

public class ServerRequest {
    public String baseURL;
    public HttpClient client;

    public ServerRequest() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        short port = ConfigFile.getConfig().SERVER_PORT;

        this.baseURL = MessageFormat.format("http://{0}:{1}", localHost.getHostAddress(), Short.toString(port));
        this.client = HttpClient.newHttpClient();
    }

    public ServerResponse addVerification(UUID uuid, String verifyCode) {
        URI targetURI = URI.create(MessageFormat.format("{0}/{1}/{2}", this.baseURL, uuid.toString(), verifyCode));
        HttpRequest httpRequest = HttpRequest
                .newBuilder(targetURI)
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("Accept", "application/json")
                .build();

        String response = client
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        return new Gson().fromJson(response, ServerResponse.class);
    }

    public @Nullable ServerVerifyResponse findVerification(UUID uuid) {
        URI targetURI = URI.create(MessageFormat.format("{0}/{1}", this.baseURL, uuid));
        HttpRequest httpRequest = HttpRequest
                .newBuilder(targetURI)
                .GET()
                .header("Accept", "application/json")
                .build();

        String response = client
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        ServerResponse serverResponse = new Gson().fromJson(response, ServerResponse.class);
        if (serverResponse.status != 200) return null;

        return new Gson().fromJson(response, ServerVerifyResponse.class);
    }

    public void teamsUpdate(Team[] data) {
        URI targetURI = URI.create(MessageFormat.format("{0}/teams", this.baseURL));
        HttpRequest httpRequest = HttpRequest
                .newBuilder(targetURI)
                .PUT(HttpRequest.BodyPublishers.noBody())
                //.PUT(HttpRequest.BodyPublishers.ofString(data))
                .headers("data", new Gson().toJson(data))
                .build();

        client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public void teamDelete(UUID uuid) {
        URI targetURI = URI.create(MessageFormat.format("{0}/{1}", this.baseURL, uuid));
        HttpRequest httpRequest = HttpRequest
                .newBuilder(targetURI)
                .DELETE()
                .header("Accept", "application/json")
                .build();

        client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
