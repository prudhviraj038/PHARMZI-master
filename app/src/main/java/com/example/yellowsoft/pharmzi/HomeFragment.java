package com.example.yellowsoft.pharmzi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.eminayar.panter.DialogType;
import com.eminayar.panter.PanterDialog;
import com.eminayar.panter.interfaces.OnSingleCallbackConfirmListener;
import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.kaopiz.kprogresshud.KProgressHUD.Style;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    HomeAdapter adapter;
    TextView apply_btn;
    ImageView close_btn;
    LinearLayout distance_btn;
    TextView distance_option;
    TextView filter;
    LinearLayout filter_btn;
    ImageView filter_image;
    LinearLayout filter_popup;
    ImageView imageView;
    ListView listView;
    MainActivity mainActivity;
    LinearLayout newest_btn;
    TextView newest_option;
    TextView phar_count;
    TextView pharma_btn;
    ArrayList<Pharmacies> pharmaciesfrom_api;
    TextView pharmcies_list;
    TextView search;
    LinearLayout search_btn;
    EditText search_edit;
    ImageView search_image;
    LinearLayout search_pop;
    TextView search_text;
    String text;
    String title;
    LinearLayout title_btn;
    TextView title_option;
    ViewFlipper viewFlipper;
    CheckBox checkbox1,checkbox2,checkbox3,checkbox4;
    String sorting_option;

    class C03941 implements OnItemClickListener {
        C03941() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(HomeFragment.this.getActivity(), ProductsActivity.class);
            intent.putExtra("pharmcies", (Serializable) HomeFragment.this.pharmaciesfrom_api.get(i));
            HomeFragment.this.startActivity(intent);
        }
    }

    class C03952 implements OnClickListener {
        C03952() {
        }

        public void onClick(View view) {
            HomeFragment.this.viewFlipper.setDisplayedChild(3);
            HomeFragment.this.search_pop.setVisibility(View.GONE);
            HomeFragment.this.filter.setTextColor(HomeFragment.this.getResources().getColor(R.color.languagecolor));
            HomeFragment.this.filter_image.setColorFilter(HomeFragment.this.getResources().getColor(R.color.languagecolor));
            HomeFragment.this.pharma_btn.setTextColor(HomeFragment.this.getResources().getColor(R.color.text_color));
            HomeFragment.this.search.setTextColor(HomeFragment.this.getResources().getColor(R.color.text_color));
            HomeFragment.this.search_image.setColorFilter(HomeFragment.this.getResources().getColor(R.color.text_color));
            if (HomeFragment.this.filter_popup.isShown()) {
                HomeFragment.this.filter_popup.setVisibility(View.GONE);
            } else {
                HomeFragment.this.filter_popup.setVisibility(View.VISIBLE);
            }
        }
    }

    class C03963 implements OnClickListener {
        C03963() {
        }

        public void onClick(View view) {
            HomeFragment.this.filter.setTextColor(HomeFragment.this.getResources().getColor(R.color.text_color));
            HomeFragment.this.filter_image.setColorFilter(HomeFragment.this.getResources().getColor(R.color.text_color));
            HomeFragment.this.pharma_btn.setTextColor(HomeFragment.this.getResources().getColor(R.color.languagecolor));
            HomeFragment.this.search.setTextColor(HomeFragment.this.getResources().getColor(R.color.text_color));
            HomeFragment.this.search_image.setColorFilter(HomeFragment.this.getResources().getColor(R.color.text_color));
            HomeFragment.this.viewFlipper.setDisplayedChild(1);
        }
    }

    class C03974 implements OnClickListener {
        C03974() {
        }

        public void onClick(View view) {
            HomeFragment.this.search_edit.setText("");
            HomeFragment.this.filter_popup.setVisibility(View.GONE);
            HomeFragment.this.search.setTextColor(HomeFragment.this.getResources().getColor(R.color.languagecolor));
            HomeFragment.this.search_image.setColorFilter(HomeFragment.this.getResources().getColor(R.color.languagecolor));
            HomeFragment.this.pharma_btn.setTextColor(HomeFragment.this.getResources().getColor(R.color.text_color));
            HomeFragment.this.filter.setTextColor(HomeFragment.this.getResources().getColor(R.color.text_color));
            HomeFragment.this.filter_image.setColorFilter(HomeFragment.this.getResources().getColor(R.color.text_color));
            if (HomeFragment.this.search_pop.isShown()) {
                HomeFragment.this.search_pop.setVisibility(View.GONE);
            } else {
                HomeFragment.this.search_pop.setVisibility(View.VISIBLE);
            }
            HomeFragment.this.viewFlipper.setDisplayedChild(2);
        }
    }

    class C03985 implements OnClickListener {
        C03985() {
        }

        public void onClick(View view) {
            HomeFragment.this.text = HomeFragment.this.search_edit.getText().toString();
            Log.e("text", HomeFragment.this.text);
            HomeFragment.this.search_pop.setVisibility(View.GONE);
            HomeFragment.this.pharmaciesfrom_api.clear();
            HomeFragment.this.adapter.notifyDataSetChanged();
            HomeFragment.this.get_pharmacies();
            Log.e("textt", HomeFragment.this.search_edit.getText().toString());
        }
    }

    class C03996 implements OnClickListener {
        C03996() {
        }

        public void onClick(View view) {
            HomeFragment.this.filter_popup.setVisibility(View.GONE);
        }
    }

    class C04007 implements OnClickListener {
        C04007() {
        }

        public void onClick(View view) {
            HomeFragment.this.title = HomeFragment.this.title_option.getText().toString();
            Log.e(Session.title, HomeFragment.this.title);
            HomeFragment.this.filter_popup.setVisibility(View.GONE);
            HomeFragment.this.pharmaciesfrom_api.clear();
            HomeFragment.this.adapter.notifyDataSetChanged();
            HomeFragment.this.get_pharmacies();
            Log.e("titl", HomeFragment.this.title_option.getText().toString());
        }
    }

    class C04018 implements OnClickListener {
        C04018() {
        }

        public void onClick(View view) {
            HomeFragment.this.title_dialog();
        }
    }

    class C04029 implements OnClickListener {
        C04029() {
        }

        public void onClick(View view) {
            HomeFragment.this.newest_dialog();
        }
    }

    public static HomeFragment newInstance(int someInt) {
        HomeFragment myFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);
        return myFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        Session.forceRTLIfSupported(getActivity());
        this.mainActivity = (MainActivity) getActivity();
        this.pharmaciesfrom_api = new ArrayList();
        this.listView = (ListView) view.findViewById(R.id.pharmacies_list);
        this.viewFlipper = (ViewFlipper) view.findViewById(R.id.filter_frame);
        this.pharma_btn = (TextView) view.findViewById(R.id.pharma_btn);
        this.filter_btn = (LinearLayout) view.findViewById(R.id.filter_btn);
        this.search_btn = (LinearLayout) view.findViewById(R.id.search_btn);
        this.search_pop = (LinearLayout) view.findViewById(R.id.search_pop);
        this.search_edit = (EditText) view.findViewById(R.id.search_edit);
        this.search_text = (TextView) view.findViewById(R.id.search_text);
        this.imageView = (ImageView) view.findViewById(R.id.image);
        this.apply_btn = (TextView) view.findViewById(R.id.apply_btn);
//        this.title_option = (TextView) view.findViewById(R.id.title_option);
//        this.newest_option = (TextView) view.findViewById(R.id.newest_option);
//        this.title_btn = (LinearLayout) view.findViewById(R.id.title_btn);
//        this.newest_btn = (LinearLayout) view.findViewById(R.id.newest_btn);
        this.filter_popup = (LinearLayout) view.findViewById(R.id.filter_popup);
        this.close_btn = (ImageView) view.findViewById(R.id.close_btn);
        this.search_image = (ImageView) view.findViewById(R.id.search_image);
        this.filter_image = (ImageView) view.findViewById(R.id.filter_image);
        this.filter = (TextView) view.findViewById(R.id.filter);
        this.search = (TextView) view.findViewById(R.id.search);
        this.phar_count = (TextView) view.findViewById(R.id.phar_count);
        checkbox1 = (CheckBox) view.findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) view.findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) view.findViewById(R.id.checkbox4);
       // this.pharmcies_list = (TextView) view.findViewById(R.id.pharmcies_list);
        this.phar_count.setText("");
        this.adapter = new HomeAdapter(getActivity(), this.pharmaciesfrom_api);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(new C03941());
        this.filter_btn.setOnClickListener(new C03952());
        this.pharma_btn.setTextColor(getResources().getColor(R.color.languagecolor));
        this.search.setTextColor(getResources().getColor(R.color.text_color));
        this.viewFlipper.setDisplayedChild(1);
        this.pharma_btn.setOnClickListener(new C03963());
        this.search_btn.setOnClickListener(new C03974());
        this.search_text.setOnClickListener(new C03985());
        this.search_edit.setText("");
        this.close_btn.setOnClickListener(new C03996());
        this.apply_btn.setOnClickListener(new C04007());
//        this.title_btn.setOnClickListener(new C04018());
  //      this.newest_btn.setOnClickListener(new C04029());
     //   this.title_option.setText("");
//        this.newest_option.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                HomeFragment.this.newest_dialog();
//            }
//        });
//        this.title_option.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                HomeFragment.this.title_dialog();
//            }
//        });

        pharma_btn.setText(Session.GetWord(getContext(),"Pharmacies"));
        search.setText(Session.GetWord(getContext(),"Search"));
        filter.setText(Session.GetWord(getContext(),"Filter"));
        if (this.mainActivity.delivery.equals("0")) {
            get_delivery_pharmacies();
        } else if (this.mainActivity.delivery.equals("1")) {
            get_pharmacies();
        }

        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                     if (checkbox1.isChecked()){
                                                         checkbox1.setChecked(true);
                                                         sorting_option = "TASC";
                                                         pharmaciesfrom_api.clear();
                                                         if (mainActivity.delivery.equals("0")) {
                                                             get_delivery_pharmacies();
                                                         } else if (mainActivity.delivery.equals("1")) {
                                                             get_pharmacies();
                                                         }
                                                         adapter.notifyDataSetChanged();
                                                         final Handler handler = new Handler();
                                                         handler.postDelayed(new Runnable() {
                                                             @Override
                                                             public void run() {
                                                                 filter_popup.setVisibility(View.GONE);
                                                             }
                                                         }, 1500);
                                                     }else {
                                                         checkbox1.setChecked(false);
                                                     }
                                                 }
                                             }
        );

        checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox2.isChecked()) {
                    checkbox2.setChecked(true);
                    sorting_option = "DESC";
                    pharmaciesfrom_api.clear();
                    if (mainActivity.delivery.equals("0")) {
                        get_delivery_pharmacies();
                    } else if (mainActivity.delivery.equals("1")) {
                        get_pharmacies();
                    }
                    adapter.notifyDataSetChanged();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            filter_popup.setVisibility(View.GONE);
                        }
                    }, 1500);
                }else {
                    checkbox2.setChecked(false);
                }
            }
        });

        checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox3.isChecked()) {
                    checkbox3.setChecked(true);
                    sorting_option = "TASC";
                    pharmaciesfrom_api.clear();
                    if (mainActivity.delivery.equals("0")) {
                        get_delivery_pharmacies();
                    } else if (mainActivity.delivery.equals("1")) {
                        get_pharmacies();
                    }
                    adapter.notifyDataSetChanged();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            filter_popup.setVisibility(View.GONE);
                        }
                    }, 1500);
                }else {
                    checkbox3.setChecked(false);
                }
            }
        });

        checkbox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox4.isChecked()) {
                    checkbox4.setChecked(true);
                    sorting_option = "TDESC";
                    pharmaciesfrom_api.clear();
                    if (mainActivity.delivery.equals("0")) {
                        get_delivery_pharmacies();
                    } else if (mainActivity.delivery.equals("1")) {
                        get_pharmacies();
                    }
                    adapter.notifyDataSetChanged();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            filter_popup.setVisibility(View.GONE);
                        }
                    }, 1500);
                }else {
                    checkbox4.setChecked(false);
                }
            }
        });

        return view;
    }

    public void title_dialog() {
        final String[] titles = new String[]{"TASC", "TDESC"};
        final PanterDialog panterDialog = new PanterDialog(getActivity());
        panterDialog.setHeaderBackground((int) R.color.languagecolor).setTitle("Select Title range").setNegative("Cancel", new OnClickListener() {
            public void onClick(View view) {
                panterDialog.dismiss();
            }
        }).setPositive("Ok").setDialogType(DialogType.SINGLECHOICE).items(titles, new OnSingleCallbackConfirmListener() {
            public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
                if (titles[pos].equals("TASC")) {
                    HomeFragment.this.title_option.setText("TASC");
                } else if (titles[pos].equals("TDESC")) {
                    HomeFragment.this.title_option.setText("TDESC");
                }
            }
        });
        panterDialog.isCancelable(false);
        panterDialog.show();
    }

    public void newest_dialog() {
        final String[] newests = new String[]{"ASC", "DESC"};
        final PanterDialog panterDialog = new PanterDialog(getActivity());
        panterDialog.setHeaderBackground((int) R.color.languagecolor).setTitle("Select Newest Range").setNegative("Cancel", new OnClickListener() {
            public void onClick(View view) {
                panterDialog.dismiss();
            }
        }).setPositive("Ok").setDialogType(DialogType.SINGLECHOICE).items(newests, new OnSingleCallbackConfirmListener() {
            public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
                if (newests[pos].equals("ASC")) {
                    HomeFragment.this.newest_option.setText("ASC");
                } else if (newests[pos].equals("DESC")) {
                    HomeFragment.this.newest_option.setText("DESC");
                }
            }
        });
        panterDialog.isCancelable(false);
        panterDialog.show();
    }

    public void get_pharmacies() {
        final KProgressHUD hud = KProgressHUD.create(getContext()).setStyle(Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
         Ion.with(getContext()).load("http://clients.mamacgroup.com/sadaleya/api/pharmacies.php").setBodyParameter("pickup", Session.GetAreaId(getActivity())).setBodyParameter("search", this.text).setBodyParameter("sorting", sorting_option).asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    Log.e("pickuppharm", result.toString());
                    Log.e(Session.area_id, Session.GetAreaId(HomeFragment.this.getActivity()));
                    hud.dismiss();
                    for (int i = 0; i < result.size(); i++) {
                        HomeFragment.this.pharmaciesfrom_api.add(new Pharmacies(result.get(i).getAsJsonObject(), HomeFragment.this.getContext()));
                        HomeFragment.this.phar_count.setText(String.valueOf(HomeFragment.this.pharmaciesfrom_api.size() + " OPEN Pharmcies "));
                        Log.e("count", String.valueOf(HomeFragment.this.pharmaciesfrom_api.size()));
                    }
                    HomeFragment.this.adapter.notifyDataSetChanged();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void get_delivery_pharmacies() {
        final KProgressHUD hud = KProgressHUD.create(getContext()).setStyle(Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(getContext()).load("http://clients.mamacgroup.com/sadaleya/api/pharmacies.php").setBodyParameter(Session.delivery, Session.GetAreaId(getActivity())).setBodyParameter("search", this.search_edit.getText().toString()).setBodyParameter("sorting",sorting_option).asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    Log.e("deli_area_id", Session.GetAreaId(HomeFragment.this.getActivity()));
                    Log.e("homepharmacies", result.toString());
                    hud.dismiss();
                    for (int i = 0; i < result.size(); i++) {
                        HomeFragment.this.pharmaciesfrom_api.add(new Pharmacies(result.get(i).getAsJsonObject(), HomeFragment.this.getContext()));
                        HomeFragment.this.phar_count.setText(String.valueOf(HomeFragment.this.pharmaciesfrom_api.size() + " OPEN Pharmcies "));
                    }
                    HomeFragment.this.adapter.notifyDataSetChanged();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
