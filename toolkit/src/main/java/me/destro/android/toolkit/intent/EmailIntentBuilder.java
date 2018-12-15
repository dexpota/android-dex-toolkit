package me.destro.android.toolkit.intent;

import android.content.Intent;
import android.net.Uri;

public final class EmailIntentBuilder {
    Intent intent;

    String scheme;
    String to;
    String subject;
    String body;

    public EmailIntentBuilder() {
        scheme = "mailto:";
    }

    public EmailIntentBuilder to(String to) {
        this.to = to;
        return this;
    }

    public EmailIntentBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailIntentBuilder body(String body) {
        this.body = body;
        return this;
    }

    public EmailIntentBuilder build() {
        String mailto = String.format("mailto:%s", this.to);
        StringBuilder parametersBuilder = new StringBuilder();

        if (subject != null) {
            if (parametersBuilder.length() > 0)
                parametersBuilder.append("&");
            parametersBuilder.append(String.format("subject=%s", Uri.encode(subject)));
        }

        if (body != null) {
            if (parametersBuilder.length() > 0)
                parametersBuilder.append("&");
            parametersBuilder.append(String.format("body=%s", Uri.encode(body)));
        }

        if (parametersBuilder.length() > 0) {
            mailto = mailto + "?" + parametersBuilder.toString();
        }

        Uri uri = Uri.parse(mailto);
        intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(uri);

        return this;
    }
}
