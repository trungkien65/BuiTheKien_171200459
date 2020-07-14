package ducchinh.example.sqlite_qlsv;

    import android.content.Context;
    import android.database.Cursor;
    import android.view.View;
    import android.widget.TextView;

    import androidx.cursoradapter.widget.ResourceCursorAdapter;

    public class SinhVienCursorAdapter extends ResourceCursorAdapter {
        public SinhVienCursorAdapter(Context context, int layout, Cursor c ) {
            super(context, layout, c );
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView txtID = (TextView) view.findViewById(R.id.textViewID);
            txtID.setText(cursor.getString(cursor.getColumnIndex(DBHelper.getID())));

            TextView txtHoTen = (TextView) view.findViewById(R.id.textViewHoTen);
            txtHoTen.setText(cursor.getString(cursor.getColumnIndex(DBHelper.getNAME())));


            TextView txtyearob = (TextView) view.findViewById(R.id.textViewNamSinh);
            txtyearob.setText(cursor.getString(cursor.getColumnIndex(DBHelper.getYEAROB())));

        }
    }
