package ru.arbuzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import api.YandexMoneyImpl;
import api.response.AccountInfoResponse;
import ru.arbuzz.R;
import ru.arbuzz.util.Config;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class PaymentActivity extends Activity {

    public static final String TOKEN = "token";

    private String token;
    private YandexMoneyImpl ym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        token = getIntent().getStringExtra(TOKEN);

        ym = new YandexMoneyImpl(Config.YANDEX_ID);
    }

    public void onAccountInfoClick(View view) {
        try {
            AccountInfoResponse response = ym.accountInfo(token);
            Toast.makeText(this, "Аккаунт: " + response.getAccount() + "\nСумма: " + response.getBalance().toString() + " " + response.getCurrency(), Toast.LENGTH_SHORT);
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка! Попробуйте позже", Toast.LENGTH_SHORT);
            Log.e("Yandex", "Yandex", e);
        }
    }
}
