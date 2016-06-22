// Method to return all entries in authors table. Returns a Cursor with 
// all authors.
    public Cursor getAuthors(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DOP", "Starting to receive all entries from authors" +
				"table.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] authors = {DatabaseInfo.AUTHORS_AUTHOR_COL};

        return db.query(DatabaseInfo.AUTHORS_TABLE, authors, null, null, 
			null, null, null);
    }