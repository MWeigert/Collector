// Method to add a new entry in authors table
    public void addAuthor(DatabaseOperations dop, String author) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.AUTHORS_AUTHOR_COL, author);
        db.insert(DatabaseInfo.AUTHORS_TABLE, null, values);

        if (debugMode) {
            Log.d("DOP", "Table authors --> added " + author + ".");
        }
    }