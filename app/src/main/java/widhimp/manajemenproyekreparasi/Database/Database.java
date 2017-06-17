package widhimp.manajemenproyekreparasi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import widhimp.manajemenproyekreparasi.Adapter.CheckListAdapter;
import widhimp.manajemenproyekreparasi.Adapter.KomunikasiAdapter;
import widhimp.manajemenproyekreparasi.Adapter.KapalAdapter;
import widhimp.manajemenproyekreparasi.Adapter.PenjadwalanAdapter;
import widhimp.manajemenproyekreparasi.Adapter.ProgresAdapter;
import widhimp.manajemenproyekreparasi.Adapter.RepairlistAdapter;
import widhimp.manajemenproyekreparasi.MutuProyek.CheckList;
import widhimp.manajemenproyekreparasi.Object.dokumen;
import widhimp.manajemenproyekreparasi.Object.kapal;
import widhimp.manajemenproyekreparasi.Object.repairlist;

/**
 * Created by Widhi Mahaputra on 10/26/2016.
 */

public class Database extends SQLiteOpenHelper {
    static final String name="manajemenreparasi.db";
    static final int version=1;
    public Database(Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + "kapal(name TEXT, type TEXT, length TEXT, " +
                "height TEXT, breadth TEXT, draft TEXT, dwt TEXT, survey TEXT, class TEXT);");
        db.execSQL("create table if not exists " + "komunikasi(nama TEXT);");
        db.execSQL("create table if not exists " + "repairlist(nama TEXT);");
        db.execSQL("create table if not exists " + "sdmgalangan(nama TEXT);");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /*public void ambillistkapal(KapalAdapter kapalAdapter, String kategori, String nama){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=null;
        if(kategori=="lihat")
        {
            cur = db.rawQuery("select * from kapal ", null);
        }
        else if(kategori=="cari")
        {
            cur = db.rawQuery("select * from kapal WHERE nama like '%" +nama +"%' ", null);
        }
        int i = 0;
        if (cur.getCount() > 0) {
            cur.moveToFirst();
        }
        while (i < cur.getCount()) {
            kapalAdapter.add(new kapal(cur.getString(cur.getColumnIndex("name"))
                    , cur.getString(cur.getColumnIndex("type"))
                    , cur.getString(cur.getColumnIndex("length"))
                    , cur.getString(cur.getColumnIndex("height"))
                    , cur.getString(cur.getColumnIndex("breadth"))
                    , cur.getString(cur.getColumnIndex("draft"))
                    , cur.getString(cur.getColumnIndex("dwt"))
                    , cur.getString(cur.getColumnIndex("survey"))
                    , cur.getString(cur.getColumnIndex("class"))
            ));
            cur.moveToNext();
            i++;
        }
    }

    public void ambillistdokumen(KomunikasiAdapter komunikasiAdapter, String kategori, String nama){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=null;
        if(kategori=="lihat")
        {
            cur = db.rawQuery("select * from komunikasi ", null);
        }
        else if(kategori=="cari")
        {
            cur = db.rawQuery("select * from komunikasi WHERE nama like '%" +nama +"%' ", null);
        }
        int i = 0;
        if (cur.getCount() > 0) {
            cur.moveToFirst();
        }
        while (i < cur.getCount()) {
            komunikasiAdapter.add(new dokumen(cur.getString(cur.getColumnIndex("nama"))));
            cur.moveToNext();
            i++;
        }
    }

    public void ambillistrepair(RepairlistAdapter repairlistAdapter, String kategori, String nama){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=null;
        if(kategori=="lihat")
        {
            cur = db.rawQuery("select * from repairlist ", null);
        }
        else if(kategori=="cari")
        {
            cur = db.rawQuery("select * from repairlist WHERE nama like '%" +nama +"%' ", null);
        }
        int i = 0;
        if (cur.getCount() > 0) {
            cur.moveToFirst();
        }
        while (i < cur.getCount()) {
            repairlistAdapter.add(new repairlist(cur.getString(cur.getColumnIndex("nama"))
            ));
            cur.moveToNext();
            i++;
        }
    }

    public void ambillistprogres(ProgresAdapter repairlistAdapter, String kategori, String nama){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=null;
        if(kategori=="lihat")
        {
            cur = db.rawQuery("select * from repairlist ", null);
        }
        else if(kategori=="cari")
        {
            cur = db.rawQuery("select * from repairlist WHERE nama like '%" +nama +"%' ", null);
        }
        int i = 0;
        if (cur.getCount() > 0) {
            cur.moveToFirst();
        }
        while (i < cur.getCount()) {
            repairlistAdapter.add(new repairlist(cur.getString(cur.getColumnIndex("nama"))
            ));
            cur.moveToNext();
            i++;
        }
    }

    public void ambillistjadwal(PenjadwalanAdapter repairlistAdapter, String kategori, String nama){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=null;
        if(kategori=="lihat")
        {
            cur = db.rawQuery("select * from repairlist ", null);
        }
        else if(kategori=="cari")
        {
            cur = db.rawQuery("select * from repairlist WHERE nama like '%" +nama +"%' ", null);
        }
        int i = 0;
        if (cur.getCount() > 0) {
            cur.moveToFirst();
        }
        while (i < cur.getCount()) {
            repairlistAdapter.add(new repairlist(cur.getString(cur.getColumnIndex("nama"))
            ));
            cur.moveToNext();
            i++;
        }
    }

    public void ambilchecklist(CheckListAdapter repairlistAdapter, String kategori, String nama){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=null;
        if(kategori=="lihat")
        {
            cur = db.rawQuery("select * from repairlist ", null);
        }
        else if(kategori=="cari")
        {
            cur = db.rawQuery("select * from repairlist WHERE nama like '%" +nama +"%' ", null);
        }
        int i = 0;
        if (cur.getCount() > 0) {
            cur.moveToFirst();
        }
        while (i < cur.getCount()) {
            repairlistAdapter.add(new repairlist(cur.getString(cur.getColumnIndex("nama"))
            ));
            cur.moveToNext();
            i++;
        }
    }

    public void insert(ContentValues masuk)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert("kapal", null, masuk);
    }

    public void insertKomunikasi(ContentValues masuk)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert("komunikasi", null, masuk);
    }

    public void insertRepairlist(ContentValues masuk)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert("repairlist", null, masuk);
    }*/
}

