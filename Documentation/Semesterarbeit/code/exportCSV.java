// Export method of the database
public void exportDatabaseCSV(DatabaseOperations dop, String 
	whereClause) throws IOException {

	SQLiteDatabase db = dop.getReadableDatabase();
	File exportDir = new File(Environment
		.getExternalStorageDirectory(), "");
	if (!exportDir.exists()) {
		exportDir.mkdirs();
	}

	File file = new File(exportDir, "collector.csv");
	try {
		file.createNewFile();
		Log.d("DOP", "File: " + file);
		CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
		Cursor curCSV = null;
		if (whereClause != null) {
			curCSV = db.rawQuery(whereClause, null);
		} else {
			curCSV = db.rawQuery("SELECT * FROM " + 
				DatabaseInfo.ITEMS_TABLE, null);
		}
		csvWrite.writeNext(curCSV.getColumnNames());
		while (curCSV.moveToNext()) {
		//Which column you want to export
		String arrStr[] = {curCSV.getString(0), curCSV.
			getString(1), curCSV.getString(2), curCSV
			.getString(3), curCSV.getString(4), curCSV
			.getString(5), curCSV.getString(6), curCSV
			.getString(7), curCSV.getString(8),	curCSV
			.getString(9), curCSV.getString(10), curCSV
			.getString(11), curCSV.getString(12), curCSV
			.getString(13), curCSV.getString(14), curCSV
			.getString(15), curCSV.getString(16), curCSV
			.getString(17)};
			csvWrite.writeNext(arrStr);
		}
		csvWrite.close();
		curCSV.close();
	} catch (Exception sqlEx) {
	Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
	}
}