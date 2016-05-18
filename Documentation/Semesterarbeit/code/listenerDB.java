 //Button listener for the database activity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mng_author:
                //Button author pressed by user.
                if (debugMode) {
                    Log.d("USERACTION", "Entering author administration.");
                }
                final Intent authorIntent = new Intent(this, TableActivity.class);
                authorIntent.putExtra("debugMode", debugMode);
                authorIntent.putExtra("tableName", "AUTHOR");
                startActivity(authorIntent);
                break;