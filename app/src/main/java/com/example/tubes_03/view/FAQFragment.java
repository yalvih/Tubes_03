package com.example.tubes_03.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tubes_03.R;
import com.example.tubes_03.model.AccordionItem;
import com.example.tubes_03.presenter.AccountPresenter;
import com.example.tubes_03.presenter.FAQPresenter;
import com.sysdata.widget.accordion.FancyAccordionView;
import com.sysdata.widget.accordion.Item;
import com.sysdata.widget.accordion.ItemAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// implements FAQPresenter.IFAQFragment
public class FAQFragment extends Fragment {

    Context context;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private FragmentListener fragmentListener;
//    private FAQPresenter presenter;
    Toast showToast;

    public static FAQFragment newInstance(String title) {
        FAQFragment fragment = new FAQFragment();
        Bundle args = new Bundle();
        args.putString("app_name", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faq_fragment, container, false);
//        this.presenter = new FAQPresenter(this);

        // get the listview
        this.expListView = view.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        this.listAdapter = new ExpandableListAdapter(this.getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.fragmentListener = (FragmentListener)context;
        }
        else {
            throw new ClassCastException(context.toString() + " must implement FragmentListener!");
        }
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Apa Itu Coronavirus ?");
        listDataHeader.add("Bagaimana Mencegah Virus Corona ?");
        listDataHeader.add("Bagaimana Pengobatan Virus Corona ?");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Virus Corona atau severe acute respiratory syndrome coronavirus 2 (SARS-CoV-2) adalah virus yang menyerang sistem pernapasan. " +
                "Penyakit karena infeksi virus ini disebut COVID-19. Virus Corona bisa menyebabkan gangguan ringan pada sistem pernapasan, " +
                "infeksi paru-paru yang berat, hingga kematian.");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add(" Saat ini, Indonesia sedang melakukan vaksinasi COVID-19 secara berkala ke masyarakat Indonesia. Meskipun vaksinasi sudah mulai di jalankan, cara pencegahan yang terbaik adalah dengan menghindari faktor-faktor yang bisa menyebabkan Anda terinfeksi virus ini, yaitu:\n" +
                "\n" +
                "Terapkan physical distancing, yaitu menjaga jarak minimal 1 meter dari orang lain, dan jangan dulu ke luar rumah kecuali ada keperluan mendesak.\n \n" +
                "Gunakan masker saat beraktivitas di tempat umum atau keramaian, termasuk saat pergi berbelanja bahan makanan dan mengikuti ibadah di hari raya, misalnya Idul Adha.\n \n" +
                "Rutin mencuci tangan dengan air dan sabun atau hand sanitizer yang mengandung alkohol minimal 60%, terutama setelah beraktivitas di luar rumah atau di tempat umum.\n \n" +
                "Jangan menyentuh mata, mulut, dan hidung sebelum mencuci tangan.\n \n" +
                "Tingkatkan daya tahan tubuh dengan pola hidup sehat, seperti mengonsumsi makanan bergizi, berolahraga secara rutin, beristirahat yang cukup, dan mencegah stres.\n \n" +
                "Hindari kontak dengan penderita COVID-19, orang yang dicurigai positif terinfeksi virus Corona, atau orang yang sedang sakit demam, batuk, atau pilek.\n \n" +
                "Tutup mulut dan hidung dengan tisu saat batuk atau bersin, kemudian buang tisu ke tempat sampah.\n \n" +
                "Jaga kebersihan benda yang sering disentuh dan kebersihan lingkungan, termasuk kebersihan rumah.\n \n \n \n" +
                "Untuk orang yang diduga terkena COVID-19 (termasuk kategori suspek dan probable) yang sebelumnya disebut sebagai ODP (orang dalam pemantauan) maupun PDP (pasien dalam pengawasan), ada beberapa langkah yang bisa dilakukan agar tidak menularkan virus Corona ke orang lain, yaitu:\n" +
                "\n" +
                "Lakukan isolasi mandiri dengan cara tinggal terpisah dari orang lain untuk sementara waktu. Bila tidak memungkinkan, gunakan kamar tidur dan kamar mandi yang berbeda dengan yang digunakan orang lain.\n \n" +
                "Jangan keluar rumah, kecuali untuk mendapatkan pengobatan.\n \n" +
                "Bila ingin ke rumah sakit saat gejala bertambah berat, sebaiknya hubungi dulu pihak rumah sakit untuk menjemput.\n \n" +
                "Larang orang lain untuk mengunjungi atau menjenguk Anda sampai Anda benar-benar sembuh.\n \n" +
                "Sebisa mungkin jangan melakukan pertemuan dengan orang yang sedang sedang sakit.\n \n" +
                "Hindari berbagi penggunaan alat makan dan minum, alat mandi, serta perlengkapan tidur dengan orang lain.\n \n" +
                "Pakai masker dan sarung tangan bila sedang berada di tempat umum atau sedang bersama orang lain.\n \n" +
                "Gunakan tisu untuk menutup mulut dan hidung bila batuk atau bersin, lalu segera buang tisu ke tempat sampah.\n \n" +
                "Kondisi-kondisi yang memerlukan penanganan langsung oleh dokter di rumah sakit, seperti melahirkan, operasi, cuci darah, atau vaksinasi anak, perlu ditangani secara berbeda dengan beberapa penyesuaian selama pandemi COVID-19. Tujuannya adalah untuk mencegah penularan virus Corona selama Anda berada di rumah sakit. Konsultasikan dengan dokter mengenai tindakan terbaik yang perlu dilakukan.");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Belum ada obat yang benar-benar efektif untuk mengatasi infeksi virus Corona atau COVID-19. Pilihan pengobatan akan disesuaikan dengan kondisi pasien dan tingkat keparahannya. Beberapa pasien dengan gejala ringan atau tanpa gejala akan di sarankan untuk melakukan protokol isolasi mandiri di rumah sambil tetap melakukan langkah pencegahan penyebaran infeksi virus Corona.\n" +
                "\n" +
                "Selain itu, dokter juga bisa memberikan beberapa beberapa langkah untuk meredakan gejalanya dan mencegah penyebaran virus corona, yaitu:\n" +
                "\n" +
                "Merujuk penderita COVID-19 yang berat untuk menjalani perawatan dan karatina di rumah sakit rujukan\n" +
                "Memberikan obat pereda demam dan nyeri yang aman dan sesuai kondisi penderita\n" +
                "Menganjurkan penderita COVID-19 untuk melakukan isolasi mandiri dan istirahat yang cukup\n" +
                "Menganjurkan penderita COVID-19 untuk banyak minum air putih untuk menjaga kadar cairan tubuh");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }


}

