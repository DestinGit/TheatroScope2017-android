package db.fr.theatroscope2017;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        StringBuilder lsb = new StringBuilder();

        try {
            /*
			 * Exemple avec le ContentResolver SQL
			 */
            ContentResolver cr = getContentResolver();
			/*
			 * Dans le ContentProvider
			 * public static final Uri CONTENT_URI = Uri.parse("content://fr.pb.sql.FournisseurSQLite");
			 */

            String lsURI = "content://utilities.provider.ProviderSQLiteVille";
            Uri uri = Uri.parse(lsURI);

            String[] tCols = {};

            // --- Utilisation de la methode query()
            // --- query(uri, tCols, restriction, tValeursRestriction, ORDER BY);
            // --- selectAll tries sur le CP
            Cursor curseur = cr.query(uri, tCols, null, null, null);

            while (curseur.moveToNext()) {
                lsb.append(curseur.getString(0) + " - " + curseur.getString(1) + " - " + curseur.getString(2)
                    + " - "  + curseur.getString(3) + "\n");
            }
            curseur.close();


        } catch (Exception e) {
            lsb.append("Erreur : " + e.getMessage());
        }

        textViewMessage.setText(lsb.toString());
    }
}
