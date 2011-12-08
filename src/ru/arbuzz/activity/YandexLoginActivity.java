package ru.arbuzz.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import api.YandexMoneyImpl;
import api.rights.AccountInfo;
import api.rights.OperationHistory;
import api.rights.Permission;
import ru.arbuzz.R;
import ru.arbuzz.util.Config;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class YandexLoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yandex_login);

        YandexMoneyImpl ym = new YandexMoneyImpl(Config.YANDEX_ID);

        Collection<Permission> scope = new LinkedList<Permission>();
        scope.add(new AccountInfo());
        scope.add(new OperationHistory());

        String codeRedirectUri = ym.authorizeUri(scope, Config.REDIRECT_URI);

        WebView web = (WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new InsideWebViewClient(this));
        web.loadUrl(codeRedirectUri);
    }

    private static class InsideWebViewClient extends WebViewClient {

        private Activity context;
        private ProgressDialog dialog;

        public InsideWebViewClient(Activity context) {
            this.context = context;
            this.dialog = new ProgressDialog(context);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (url.startsWith(Config.REDIRECT_URI)) {
                String code = url.substring(url.indexOf("code=") + 5);
                Intent intent = new Intent(context, PaymentActivity.class);
                intent.putExtra(PaymentActivity.TOKEN, code);
                context.startActivity(intent);
            }
            dialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            dialog.dismiss();
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }


    }
}
