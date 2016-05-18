// Fill authors from database to list item
                String author;
                final ArrayList<String> authors = new ArrayList<String>();
                Cursor authorCrs = db.getAuthors(db);
                anz = authorCrs.getCount();

                if (debugMode) {
                    Log.d("TABAC", "DATABASE: " + anz.toString() + " authors in table.");
                }
                if (anz > 0) {
                    authorCrs.moveToFirst();
                    do {
                        int index = authorCrs.getColumnIndex(DatabaseInfo.AUTHORS_AUTHOR_COL);
                        if (debugMode) {
                            Log.d("TABAC", "Index (AUTHORS_AUTHOR): " + index);
                        }
                        author = authorCrs.getString(index);
                        if (debugMode) {
                            Log.d("TABAC", "DATABASE: Get " + author);
                        }
                        authors.add(author);
                        if (debugMode) {
                            Log.d("TABAC", "Size of authors = " + authors.size());
                        }
                    } while (authorCrs.moveToNext());

                    if (debugMode) {
                        Log.d("TABAC", "Putting data in Adapter");
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, authors);

                    LIST.setAdapter(adapter);
                }