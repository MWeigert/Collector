// Method which returns a string with an SQL select statement from given
// item data
    public String getSelectStatement(Item item) {

        String whereClause = "SELECT " + DatabaseInfo.ITEMS_ID_COL + 
		" FROM " + DatabaseInfo.ITEMS_TABLE + " WHERE ";

        if (item.getEAN() > 0) whereClause += DatabaseInfo.ITEMS_EAN_COL + 
		"=" + Long.toString(item.getEAN()) + " AND ";
		.
		.
		.
		.
		.
        if (item.isDvd()) whereClause += DatabaseInfo.ITEMS_DVD_COL + 
		"=1 AND ";
        if (item.isBluRay()) whereClause += DatabaseInfo.ITEMS_BLURAY_COL + 
		"=1 AND ";
        if (!item.getStudio().equals("STUDIO")) whereClause += 
		DatabaseInfo.ITEMS_STUDIO_ID_COL + "=" + Integer
                .toString(item.getStudio_id()) + " AND ";
        if (!item.getDirector().equals("DIRECTOR")) whereClause += 
		DatabaseInfo.ITEMS_DIRECTOR_ID_COL + "=" + Integer
                .toString(item.getDirector_id()) + " AND ";
        if (item.getFsk() != 42)
            whereClause += DatabaseInfo.ITEMS_PARENTAL_ID_COL + "=" + 
		Integer.toString(item.getFsk()) + " " + "AND ";
        whereClause = whereClause.substring(0, whereClause.length() - 4) +
		";";
        return whereClause;
    }