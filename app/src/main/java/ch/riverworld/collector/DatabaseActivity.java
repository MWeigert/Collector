// ********************************************************************************************
// *                                                                                          *
// *                                        SEMESTERARBEIT                                    *
// *                                             ZHAW                                         *
// *                                                                                          *
// * Programmed by: Mathias Weigert                                                           *
// *       Version: 0.02                                                                      *
// *          Year: 2016                                                                      *
// *                                                                                          *
// ********************************************************************************************/

package ch.riverworld.collector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    private boolean debugMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }
    }

    //Button listener for the database activity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mng_author:
                //Button author pressed by user.
                if (debugMode) {
                    String msg = "Entering author administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                final Intent authorIntent = new Intent(this, TableActivity.class);
                authorIntent.putExtra("debugMode", debugMode);
                authorIntent.putExtra("tableName", "AUTHOR");
                startActivity(authorIntent);
                break;
            case R.id.btn_reset_author:
                //Button reset author pressed by user.
                AlertDialog.Builder authorBuilder = new AlertDialog.Builder(DatabaseActivity.this);
                authorBuilder.setMessage("Do you really want to reset the complete author table?");

                authorBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        DatabaseOperations db = new DatabaseOperations(DatabaseActivity.this, debugMode);
                        db.resetAuthors(db);
                    }
                });

                authorBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        String msg = "FALSE";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog authorAlert = authorBuilder.create();
                authorAlert.show();
                break;
            case R.id.btn_mng_director:
                //Button director pressed by user.
                if (debugMode) {
                    String msg = "Entering director administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                final Intent directorIntent = new Intent(this, TableActivity.class);
                directorIntent.putExtra("debugMode", debugMode);
                directorIntent.putExtra("tableName", "DIRECTOR");
                startActivity(directorIntent);
                break;
            case R.id.btn_reset_director:
                //Button reset director pressed by user.
                AlertDialog.Builder directorBuilder = new AlertDialog.Builder(DatabaseActivity.this);
                directorBuilder.setMessage("Do you really want to reset the complete director table?");

                directorBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        DatabaseOperations db = new DatabaseOperations(DatabaseActivity.this, debugMode);
                        db.resetDirectors(db);
                    }
                });

                directorBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        String msg = "FALSE";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog directorAlert = directorBuilder.create();
                directorAlert.show();
                break;
            case R.id.btn_friends:
                if (debugMode) {
                    String msg = "Entering friends administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                final Intent friendsIntent = new Intent(this, FriendsActivity.class);
                friendsIntent.putExtra("debugMode", debugMode);
                startActivity(friendsIntent);
                break;
            case R.id.btn_reset_friends:
                //Pressed button to add new friend to the database.

                if (debugMode) {
                    String msg = "Table friends reseted.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(DatabaseActivity.this);

                builder.setMessage("Do you really want to reset the complete friends table?");
                //builder.setCancelable(true);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        DatabaseOperations db = new DatabaseOperations(DatabaseActivity.this, debugMode);
                        db.resetFriends(db);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.btn_mng_genre:
                //Button genre pressed by user.
                if (debugMode) {
                    String msg = "Entering genre administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                final Intent genreIntent = new Intent(this, TableActivity.class);
                genreIntent.putExtra("debugMode", debugMode);
                genreIntent.putExtra("tableName", "GENRE");
                startActivity(genreIntent);
                break;
            case R.id.btn_reset_genre:
                //Button reset genre pressed by user.
                AlertDialog.Builder genreBuilder = new AlertDialog.Builder(DatabaseActivity.this);
                genreBuilder.setMessage("Do you really want to reset the complete genre table?");

                genreBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        DatabaseOperations db = new DatabaseOperations(DatabaseActivity.this, debugMode);
                        db.resetGenres(db);
                    }
                });

                genreBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        String msg = "FALSE";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog genreAlert = genreBuilder.create();
                genreAlert.show();

                break;
            case R.id.btn_language:
                //Button language pressed by user.
                if (debugMode) {
                    String msg = "Entering language administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                final Intent languageIntent = new Intent(this, TableActivity.class);
                languageIntent.putExtra("debugMode", debugMode);
                languageIntent.putExtra("tableName", "LANGUAGE");
                startActivity(languageIntent);
                break;
            case R.id.btn_reset_language:
                //Button reset language pressed by user.
                AlertDialog.Builder langBuilder = new AlertDialog.Builder(DatabaseActivity.this);
                langBuilder.setMessage("Do you really want to reset the complete language table?");

                langBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        DatabaseOperations db = new DatabaseOperations(DatabaseActivity.this, debugMode);
                        db.resetLanguage(db);
                    }
                });

                langBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        String msg = "FALSE";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog langAlert = langBuilder.create();
                langAlert.show();
                break;

            case R.id.btn_mng_publisher:
                //Button publisher pressed by user.
                if (debugMode) {
                    String msg = "Entering publisher administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                final Intent publisherIntent = new Intent(this, TableActivity.class);
                publisherIntent.putExtra("debugMode", debugMode);
                publisherIntent.putExtra("tableName", "PUBLISHER");
                startActivity(publisherIntent);
                break;
            case R.id.btn_reset_publisher:
                //Button reset publisher pressed by user.
                AlertDialog.Builder publisherBuilder = new AlertDialog.Builder(DatabaseActivity.this);
                publisherBuilder.setMessage("Do you really want to reset the complete publishers table?");

                publisherBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        DatabaseOperations db = new DatabaseOperations(DatabaseActivity.this, debugMode);
                        db.resetPublishers(db);
                    }
                });

                publisherBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        String msg = "FALSE";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog publisherAlert = publisherBuilder.create();
                publisherAlert.show();
                break;
            case R.id.btn_mng_studio:
                //Button studio pressed by user.
                if (debugMode) {
                    String msg = "Entering studio administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                final Intent studioIntent = new Intent(this, TableActivity.class);
                studioIntent.putExtra("debugMode", debugMode);
                studioIntent.putExtra("tableName", "STUDIO");
                startActivity(studioIntent);
                break;
            case R.id.btn_reset_studio:
                //Button reset studio pressed by user.
                AlertDialog.Builder studioBuilder = new AlertDialog.Builder(DatabaseActivity.this);
                studioBuilder.setMessage("Do you really want to reset the complete studios table?");

                studioBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        DatabaseOperations db = new DatabaseOperations(DatabaseActivity.this, debugMode);
                        db.resetStudios(db);
                    }
                });

                studioBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        String msg = "FALSE";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog studioAlert = studioBuilder.create();
                studioAlert.show();
                break;
            case R.id.btn_mng_system:
                //Button system pressed by user.
                if (debugMode) {
                    String msg = "Entering system administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                final Intent sysIntent = new Intent(this, TableActivity.class);
                sysIntent.putExtra("debugMode", debugMode);
                sysIntent.putExtra("tableName", "SYSTEM");
                startActivity(sysIntent);
                break;
            case R.id.btn_reset_system:
                //Button reset system pressed by user.
                AlertDialog.Builder sysBuilder = new AlertDialog.Builder(DatabaseActivity.this);
                sysBuilder.setMessage("Do you really want to reset the complete systems table?");

                sysBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        DatabaseOperations db = new DatabaseOperations(DatabaseActivity.this, debugMode);
                        db.resetSystems(db);
                    }
                });

                sysBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        String msg = "FALSE";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog sysAlert = sysBuilder.create();
                sysAlert.show();
                break;
        }
    }
}
