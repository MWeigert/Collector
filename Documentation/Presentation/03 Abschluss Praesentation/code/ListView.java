// Controller einer ListView
if (anz > 0) {
            int indexItemId = crs.getColumnIndex(DatabaseInfo.ITEMS_ID_COL);
            do {
                int id = crs.getInt(indexItemId);
                Item item = new Item(ctx, id);
                items.add(item);
            } while (crs.moveToNext());

            final ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, items);

            list.setAdapter(adapter);
        }

// Class: Item 
@Override
    public String toString() {
        return title;
    }