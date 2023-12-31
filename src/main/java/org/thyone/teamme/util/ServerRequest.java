package org.thyone.teamme.util;

import com.google.gson.Gson;
import org.jetbrains.annotations.Nullable;
import org.thyone.teamme.database.ConfigFile;
import org.thyone.teamme.model.ServerResponse;
import org.thyone.teamme.model.ServerVerifyResponse;
import org.thyone.teamme.model.Team;

import java.io.IOException;
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

    //verify

    public ServerResponse createVerification(UUID uuid, String verifyCode) {
        URI targetURI = URI.create(MessageFormat.format("{0}/verify/create/{1}/{2}", this.baseURL, uuid, verifyCode));
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(targetURI)
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        String response = client
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        return new Gson().fromJson(response, ServerResponse.class);
    }

    public @Nullable ServerVerifyResponse deleteVerification(UUID uuid) {
        URI targetURI = URI.create(MessageFormat.format("{0}/verify/delete/{1}", this.baseURL, uuid));
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(targetURI)
                .header("Accept", "application/json")
                .DELETE()
                .build();

        String response = client
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        ServerResponse serverResponse = new Gson().fromJson(response, ServerResponse.class);
        if (serverResponse.status != 200) return null;

        return new Gson().fromJson(response, ServerVerifyResponse.class);
    }

    //team

    public void teamUpdate(Team[] data) throws IOException, InterruptedException {
        URI targetURI = URI.create(MessageFormat.format("{0}/team/update", this.baseURL));

        String body = new Gson().toJson(data);
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(targetURI)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();

        client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public void teamDelete(UUID uuid) {
        URI targetURI = URI.create(MessageFormat.format("{0}/team/delete/{1}", this.baseURL, uuid));
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(targetURI)
                .header("Accept", "application/json")
                .DELETE()
                .build();

        client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    //notify

    public void notifyStart() {
        URI targetURI = URI.create(MessageFormat.format("{0}/notify/server/start/{1}", this.baseURL, ConfigFile.getConfig().PUBLIC_ADDRESS));
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(targetURI)
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public void notifyStop() {
        URI targetURI = URI.create(MessageFormat.format("{0}/notify/server/stop/{1}", this.baseURL, ConfigFile.getConfig().PUBLIC_ADDRESS));
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(targetURI)
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
