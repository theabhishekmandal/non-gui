package dataStructures.Tree.BinaryTree.Problems;

import java.util.*;

/*
Given a set of reviews provided by the customers for different hotels and a string containing Good Words, you need to sort the reviews in descending order according to their Goodness Value (Higher goodness value first). We define the Goodness Value of a string as the number of Good Words in that string.

NOTE: Sorting should be stable. If review i and review j have the same Goodness Value then their original order would be preserved.

You are expected to use Trie in an Interview for such problems

Problem Constraints

    1 <= No.of reviews <= 200
    1 <= No. of words in a review <= 1000
    1 <= Length of an individual review <= 10,000
    1 <= Number of Good Words <= 10,000
    1 <= Length of an individual Good Word <= 4
    All the alphabets are lower case (a - z)

Input Format

First argument is a string A containing "Good Words" separated by "_" character

Second argument is a vector B of strings containing Hotel Reviews. Review strings are also separated by "_" character.

Output Format

Return a vector of integers which contain the original indexes of the reviews in the sorted order of reviews.


Example Input

Input 1:

 A = "cool_ice_wifi"
 B = ["water_is_cool", "cold_ice_drink", "cool_wifi_speed"]

Example Output

Output 1:

 [2, 0, 1]



Example Explanation

Explanation 1:

 sorted reviews are ["cool_wifi_speed", "water_is_cool", "cold_ice_drink"]
 */
class TrieNode{
    private boolean isWord;
    private final Map<Character, TrieNode> children;
    public TrieNode(){
        this.isWord = false;
        this.children = new HashMap<>();
    }
    public boolean getWord(){
        return this.isWord;
    }
    public void setWord(){
        this.isWord = true;
    }
    public Map<Character, TrieNode> getChildren(){
        return this.children;
    }
}

class word {
    private final int order;
    private final String word;
    private final int numberOfWords;
    public String getWord(){
        return this.word;
    }
    public int getNumberOfWords(){
        return this.numberOfWords;
    }
    public int getOrder(){
        return this.order;
    }
    public word(String word, int num, int order){
        this.word = word;
        this.numberOfWords = num;
        this.order = order;
    }
    public static word of(String word, int numberOfWords, int order){
        return new word(word, numberOfWords, order);
    }
}

public class CountWordsPresentInTrie {
    private static final TrieNode root = new TrieNode();

    // insert the character if not present and make it the new temp
    private static void insert(String str){
        TrieNode temp = root;
        for(int i = 0; i < str.length(); i++){
            temp.getChildren().putIfAbsent(str.charAt(i), new TrieNode());
            temp = temp.getChildren().get(str.charAt(i));
        }
        temp.setWord();
    }

    // if the word is present in the trie then return true otherwise false
    private static boolean isPresent(String string){
        TrieNode temp = root;
        for(char c : string.toCharArray()){
            if(!temp.getChildren().containsKey(c)){
                return false;
            }
            temp = temp.getChildren().get(c);
        }
        return temp != null && temp.getWord();
    }

    private static List<Integer> solve(String goodWords, ArrayList<String> hotelReviews){
        String[] goodWordsArray = goodWords.split("_");

        // insert all the good words separated by _ in the trie
        for(String string : goodWordsArray){
            insert(string);
        }
        List<word> list = new ArrayList<>();
        int index = 0;

        // check for each word in hotel review separated by _ in trie
        // if it is present then count the number of words
        for(String string : hotelReviews){
            int contains = 0;
            for(String sep : string.split("_")){
                contains = (isPresent(sep))? contains + 1 : contains;
            }
            list.add(word.of(string, contains, index++));
        }

        // sorting in descending order on the basis of number of goodWords found
        // if no. of goodWords are same for some value then sort on the basis of their original index
        Comparator<word> comp = Comparator.comparing(word::getNumberOfWords).thenComparingInt(x -> -x.getOrder());
        list.sort(comp.reversed());

        ArrayList<Integer> indexList = new ArrayList<>();
        list.forEach(x -> indexList.add(x.getOrder()));
        return indexList;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String goodWords = s.next();
        s.nextLine();
        String[] hotelReviews = s.nextLine().split("\\s+");
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(hotelReviews));
        System.out.println(solve(goodWords, arr));
    }
}

/*
InputString

cool_ice_wifi
water_is_cool cold_ice_drink cool_wifi_speed



qghu_eay_nl_dxfi_cvsc_gg_wk_nqdu_wf_fozv_rtk_pr_p_g_rp_rvys_mwcy_y_c_pev_kef_mz_imkk_svw_r_nzk_cxf_tl_gyp_fad_oo_fxz_co_juv_vabo_gpo_ylf_bnpl_vrvi_ya_yeh_q_qrqp_x_j_loov_o_u_wh_s_cb_coks_zkva_xdkn_yj_h_ixjs_nkk_f_ux_zr_bmnm_q_oke_ly_nk_aug_qrcd_iute_ojw_yyz_vs_m_sa_lfvg_b_aaov_zy_nt_kdcp_s_te_j_hdi_co_zc_fw_qi_tv_wvxh
ldvgylw_b_sbm_o_xtlhcsm_xo_gmgnkeu_dxot_gb_x_eyanfe_cukep_s_kljuggg_kjd_z_enpe_qgxiepj_r_z_azuj_lchhbfq_kim_zobiw_bx_uunfsks_s_tekmqd_yzje_uhms_qcoziji_fi_need_ps_r_av_mmt_tbdzqs_e_uvnpp_suacbaz_x_hec_h_egrpu_kdm_pp_eqt_j_par_owz_qyo_ytjbbh_wdy_cprjbx_ho_hp_wq_uhrqz_nbnf_v_nqq_rz_pxiog_li_xdzu_osrkrus_ojbrzm_zpow_jil_fra_md_gp_puu_gxpqnjw_mwax_mns_hh_qqrz_dlt_zo_cjt_zxuglsd_mzcn_ckvfaj_rmxot_o_kbjzwu_wljfrim_myhchz_iwkbarx_gfc_ceyhju_i_wtbvtre  bc_xifbx_fbcg_cfqckco_zgkub_j_mbsz_ssh_roefwsj_xjhgu_yup_w_eiqur_ixiqfld_uveoowq_udhn_fnj_aimu_zfsku_dubu_iswtb_ecuyka_fcvkdz_ztoiduk_hjzefc_zzb_kqd_qz_kfob_cdhth_djgkjel_lpax_m_eroswi_dpt_cclifke_jyti_rc_aybnef_n_vg_e_yyhngy_dr_dmp_meckot_wosp_f_hfo_qvlqf_w_kmfxd_ygmdc_sz_go_sodkjg_cwmb_xrm_uyfyqg_j_kcklzna_xq_qoyzwm_ub_azcpk_ktky_zivcu_purfmbi_gekyr_z_xdhpo_mvafy_a_xsvkht_diher_igbhzjz_jx_myspnar_ew_egjccv_hrjvbj_sqdjo_tgpk_fpfyc_fie_wqrwwwp_sqmet_ge_spxnv_iupal_ynmkm_uvkl_secdwra_gfmzk_ip_fod_jmjq_iqpu  him_f_uzw_vij_f_llkjdu_sj_fbtlkm_qrmyj_jnh_ssqct_dt_amdc_bprh_neg_i_xgc_wlg_sme_earwtvj_jbaoi_jlwh_pnvruih_swk_fy_tydha_wyhsgew_mtgonzl_j_g_uhn_hre_gjf_k_smt_jhae_qzaaul_rchjccd_rfvvr_vuye_gfi_dr_ygu_qdr_dakubn_guproq_l_bcwqxk_mau_jgmhcm_gdnm_hnq_amhur_trff_clvgr_kkldacl_teojom_nxrq_jz_in_nnzwac_xaedr_ud_zrfusew_tboxvy_fhks_ce_au_ndd_fdmvzca_tdc_kxa_ydzsx_tobbg_ngv_pjg_joglmkx_bfcpypc_qchbd_zwr_bzmqrl_vobt_hxgin_gf_cclmznm_ugw_bs_fc_hubsjol_msqsg_mc_hel_otf_bgsf_pc srup_hynvzh_pqugriw_iqx_fjpw_xfblk_npeelf_mtk_q_zomwn_m_upmk_lptn_mpdsy_sgvfp_n_m_bori_suq_cesk_kh_smvnon_afxwhg_i_a_vqopqfo_i_ssqfqw_htxdz_jtl_txmrj_xwtlg_ky_bioly_nilqa_oj_kk_fxahh_mb_cl_ari_tdwcldv_xropby_zwyy_ju_thwml_r_lfzdz_btu_xuoffv_crswsa_nmoi_o_vvgobqp_ckwvnhk_bmtd_vygkj_sux_atmuud_bhmkn_fx_xq_kjlzz_tsjfa_ed_uujkolx_oqkdvfe_vlhvhr_tfdukx_f_psswyxl_jjhev_yxozbaf_fmowg_gonu_tdql_hyg_yljdd_hm_te_z_odsrke_tg_nknta_jkpxino_gzdthun_oox_jjmp_vknhk_j_pmmle_k_ucvzqly_nwcmb_bh lowpjb_yv_tgtoq_micxa_zarknnx_uufa_zros_qws_tcj_hecqudo_rtj_yaaykqr_ycrxu_jxnpqjn_kc_dmoka_xap_mvbq_zsvxzk_tap_wgzp_py_kz_vb_t_vfx_xpjaox_f_vxxytg_krcxa_tauqgn_mph_zy_ayab_rqgep_xyjstty_uv_dvybs_x_bm_zv_tnovid_npghoz_afmsnsn_iv_vubc_tfsrq_mknepbh_wejazh_wc_mtpixx_lzqys_tww_ai_yax_ql_prxi_exxyfv_ddygt_c_yyfw_js_ybglgz_bi_tat_nhodto_p_zwotkgn_u_hpk_ea_owz_bsdnvq_kfcmo_i_frfqgvp_vor_j_zslzm_jjn_hrykxjz_bua_clfkcyz_bgh_uox_ajvpre_wgxxp_w_gmmve_ew_mfnq_cuqd_cili_ecihlui mfcswtw_kxl_bhqkvc_wjebkr_lp_fgratz_sguvz_f_hy_wjsjavw_uxpanna_x_dvz_hnacz_hepwzo_husl_kvwpnvg_mii_w_dwdf_lk_bqdxgy_i_dsj_ezkme_bjlk_ertahne_huqmjic_musq_k_asol_jxpt_xeumz_wyucpab_seff_nqpkfnn_ecbb_j_dyj_ibitllp_xel_dkexdtq_vptte_ht_qlbbbv_q_zkyna_yrba_myj_zxnd_iy_sexwb_o_wq_qrtcdlp_mv_if_gymkgo_zjm_z_tmrpnd_vnmt_j_tss_aq_j_d_y_w_yatml_yqs_kpa_svbm_xrlli_fed_jiga_yxjveqv_bacui_aipyhbb_ipbzncw_rbli_izxoqpt_qweaa_jeqiozp_fauuqmv_x_mn_msyamp_lza_otlsl_uhtf_jr_wbwhmq_zkhdkcr_v_eiyipvf zhyuj_b_q_wtbkdog_emhibyx_nx_xrzl_tbqex_rqoier_apl_yjpq_ubvj_eba_wnk_vloid_fpir_ycztwzz_ewx_t_kud_pe_upkczl_wuskd_e_dcplbk_pjmpfdc_qtr_v_v_rffvpe_k_mc_p_y_auteuv_zvicthr_sxclprg_l_xfaigay_ueicufd_a_ht_bxotd_rxxgv_qmeyr_vfichq_ivfj_auqf_ft_mops_gpu_xtuhl_vspihaz_ss_s_wxb_murwmx_dbhumr_jqmvnky_tnsjvw_xssq_njomuy_njuwrs_xwqy_xcszp_klwjd_ltbsc_edpi_laqi_es_sqh_j_rglmw_nrxr_nwa_ztg_h_aunfq_v_iohnjq_jshym_ovw_efhjesv_tiziojl_dp_oxby_yop_jkztvj_kwxk_wpzhbjs_thsyoe_jg_wyeozlu_mg_b_s_rhcr_x vcaxx_ufahyya_zk_u_jrjsvo_hty_wpbs_xsbz_yrvgq_lunws_pirzf_bt_eplkgwf_hrouoy_rwmqoj_hyq_fevs_com_yhgdml_ukwyzc_rskwgx_lsuc_bs_pjeiszs_cezwa_qrvlw_gr_vjcdzx_p_ql_rz_hm_zr_o_rqe_kzfb_djiems_zbhz_nw_wmxxg_pylrax_lmtbi_lbhc_hl_yoyhgm_oppr_axqmom_yhg_giupjq_z_thtop_t_it_qwt_e_onxwz_huti_klgk_r_uwkms_qc_uivahq_tms_kmaalk_csnxuc_vtykr_awzadjx_x_mnd_ldt_xsknx_ezn_sqtfb_cfqm_hbvssii_vijsuxi_caa_mkwyw_tjzppkw_bmjahtp_t_e_p_bmu_gyrhl_h_wgjp_w_hs_z_avqyec_toz_abnan_rrbd_ndo  euiozio_uoz_qt_hw_uroqr_qwcvlx_ipodi_spqnzo_cwva_t_rgdf_y_tkwwq_t_iuu_vga_hfshc_xhuuyy_tgdglf_ieb_ut_eunfh_axphbs_wztp_jn_jnc_xrjvbv_cef_bemg_dym_mmkw_ehfkr_qbyv_cmpes_omdxl_ubr_yes_qlzmr_wqnk_itxgii_q_sk_tcznlt_rxbltz_dyzva_nkixjw_ztdjp_suow_v_ccock_rjbfrjp_gd_dr_dnp_srjgdr_rv_tv_pazh_t_qvcclpe_tsjpy_zf_otqpoxm_hhwewnv_bvdk_zififr_wqqeoj_eqbnsk_zeew_k_oj_rs_gsczvhh_jzum_g_fhw_k_flu_z_ctka_x_smfa_wins_lsojtrx_wm_l_bypec_eiw_s_neuiqu_pgugwg_mikdwi_k_il pdqsvmt_rdf_lhw_afbga_csfij_l_ii_ofgxei_pbtyw_za_kkca_comvntm_h_xlef_pyebeoe_znbhy_wmfyaq_ian_yh_oui_veav_zjwobl_gwnk_jlni_qcwv_xt_thy_muxqkw_m_f_s_glgc_xpi_ytd_a_r_k_okmwgsd_pqkrzyj_hzt_iepagwh_oq_hwu_bcihpq_aqzxu_kvakvla_kswf_sdbvq_dht_cde_tmpphk_h_x_rpufw_jru_ksslhl_x_y_xv_osag_m_bgz_bafazjo_q_swtorc_qaxsreh_rlyjiwt_hglkoz_kmmxty_oxh_kxopv_bjzox_zu_dnt_amzot_cjtue_mpqk_db_gckjqte_ichz_pprqi_wbd_oy_csaetl_bfviocf_xwdrn_ffbf_vyxzu_j_ocyhzq_z tbrrrl_kt_hfnctp_rnaw_nydwwv_abuqcim_nlh_odem_km_nmaxi_mkeywc_fq_k_mzoi_gqipz_yixryd_sovh_t_gzskwpt_qcexr_nxsk_wndqizl_k_n_ssa_qazh_n_nvi_tko_afme_cgguz_h_djzz_erus_avnvsl_noasr_drnldkl_kfz_agi_nat_zwxclkk_ln_aq_ton_qlnbto_db_ivazxz_cojdhi_wka_iagt_taf_svpph_raaaixz_w_yruumq_dje_vvc_oid_mtpbnm_u_o_iwpu_czdapd_g_avviyir_jno_sowr_vvji_swric_zsx_vd_amnfhwf_eohs_kefutx_ibh_ulnlwx_wwzdgd_g_qwapbhl_udoem_nt_o_ah_xjh_j_epl_triabwu_rj_bv_ozvmx atkhqsq_hlhtbof_ikw_f_co_uun_fhp_deyho_quw_vwt_hhhu_jzmfe_inipbnc_zoc_a_da_ljr_qtkhudb_prz_sr_xoc_slut_qzsq_ijvc_npflrlu_diaohqj_raglgr_yk_dkd_r_rxjstcw_sxgywfn_c_kpm_supmt_vi_f_ktbw_fahoevn_xbrhhf_bgywc_hktdox_tek_wwurzb_mld_fibdv_mun_lxuby_vvrgk_rerptis_zxhre_nx_u_zxkug_ooh_hudidg_htjhy_zkyhpf_wgpr_seaccxk_vixvf_qfawkf_jp_e_v_ggatf_ekixeyr_c_xsc_kcfgq_yhjm_timd_pi_hwzi_cyucegl_mb_y_fvtqvzq_txl_i_yz_ki_jxmb_mkqlwh_ng_lv_zgppxo_eoqxppb_mblx_keofmni_mjc c_yvd_ueter_pjj_aknkxt_zqfljnw_xgkfyl_ukb_ysp_khjuc_uw_hzv_hjbj_o_dlnbf_kvbemw_tfjynq_gzagbk_ekkou_dcynk_tosf_tl_c_ntoilf_ldvrxl_nv_xzecxep_mzzgxlu_hmj_ec_ldjesd_pjikvpa_dvkxgll_s_a_b_vjkdq_kdorj_nyg_hhptz_fawftz_gem_hpqyict_oyfpcv_cao_akjacqa_zyo_dz_fqhk_lfbyzmn_mdlxm_yda_yjnecx_jj_hi_nvoc_enqmwi_xxzoqf_tpik_qu_mo_ikp_zhd_fqsgbx_isyz_nhxyf_ydfm_dwomci_dc_yndgc_f_it_pmymx_i_nebpyw_gqs_djplwz_mjoopyy_fvkm_w_gp_jeawp_ap_hoxuuf_b_whpe_coqrg_qwz_ibn khzm_bzfh_un_k_ibv_szzn_pfg_xzgfl_lsxb_umi_oc_t_cozgtz_u_uz_mxeekwn_sb_crfx_iogc_eozpscr_jotyczc_m_aaols_ubpyl_nmwajgx_gyz_ov_nfvo_ikq_txgvenk_ppww_lhxtg_bvmajey_a_egsei_i_cmducl_aioiss_siypp_zdu_pts_veszs_ppqmotj_k_dnvq_bkat_ef_lgexk_rmhdd_pswp_fv_vujiu_bmi_ructtm_vznjrch_wzwmoze_rznjkfr_k_mrdwr_a_c_pktl_wxwtn_a_q_lrwbgb_j_l_hiiull_ovbe_lwlpb_yffh_avhf_ahli_djdfpek_glix_axxtolh_lvwxk_vu_cy_e_ll_qzh_tm_tujdcuu_nro_lot_p_nyfh_jeg_mf_t_mveghmf_mv_lsq  bvxct_pvmljoh_xx_olnvlay_u_kqda_ptyvjaf_zkq_axjlrb_ukkki_jmxq_bazlp_e_pez_bwshv_ckldqgi_lqpir_vwqq_yxpi_qi_e_qamf_svvntgu_es_drnwwt_kdo_fpkdu_l_f_dpohcgu_ku_ehrqb_xaj_fb_hk_b_kapb_zyspda_oxra_jotju_stu_tjhu_petsg_zuigu_nwlyrvf_kkm_d_uhvh_q_dvtn_ewfqu_htai_kebbvz_tqbkdbm_hbsnj_tvjtq_bchi_fuqlzsm_vfjtv_uitiv_hz_xcd_wikfat_anchxs_jluhl_oebcgn_qfzxxw_liuejob_fpvnyrr_kege_pvlhxe_ocmerj_ziudts_q_zvejnr_ib_n_epfqd_m_s_mmwwwq_nnrmqmj_cpao_azfeur_pbfryed_jrltmy_hysoicw_ohfere_eaca_uvjoxy_pmsguf_iwlcfyw_te_yqdhxwj_iudghy_yo_b_ceftdr lxfv_fbbjjyf_vkvsap_shakg_vhcfm_ezt_jc_hxlhasq_au_lnfhgg_ag_nf_m_pinwlex_qxis_fnhjz_v_x_uky_zcru_mib_cpjz_naclmnu_nbdif_lujqv_f_xxvkc_fkzzjqn_qpoh_gjs_takzrso_yqh_uhk_cjyugt_d_gfur_xiyqu_otiajm_hyilthm_evlwl_xpwpzhs_b_zvtv_wyc_z_maomaf_yvyr_pizteuo_gthfxsm_jrwps_rsgw_hhilgdh_fza_adtklhy_qzfd_msruee_xkq_rudlmz_dtnh_ivnnkc_gar_gydqdh_trsth_dzyqaz_novk_qcotch_ol_neiyt_mrj_cyo_ivdyhph_jzhez_hmi_rc_blvwaa_iq_g_aoqmemj_hgoxpru_tymkkt_bnoxwtt_ee_imlhczt_typhme_dsuyvkk_op_mzdc_kpjvozi_hx hd_iqed_ngwg_ooupne_k_dg_mhhd_qb_qrfpd_s_khb_tsn_wjp_gde_bjg_dyf_ecqqn_emkimip_cehlbt_bwnkd_ymec_s_hvkm_w_xesho_hml_v_iljv_llfpn_zywcpam_vwtyg_phif_bttnwnp_dby_wodclr_prseqw_d_svyncg_razz_odggxw_b_jqujrm_dpomjr_ledhu_hwk_zktn_ytgjpmm_v_jcnjgzu_wqq_ldzcs_ojswrqc_bqyxaag_bpvtx_onpea_hn_oh_vo_ldxh_prngs_qdjfqt_xyr_izz_eowa_ezdlmw_fcoz_qciew_kek_grc_vzs_hpszob_ix_xk_i_lqlj_kvnbi_pdkyebc_lfkff_lzzmkvm_nl_ivwhwzt_rb_j_rtu_txfkidx_oafye_yvizvoz_qor_mqles_xr_erzo_wrv_pmi_h ff_vkoukdw_cv_qgl_chjagqu_hpn_k_cpmj_glu_hrbef_jfytk_lbnvmdw_y_hgscz_yj_d_gn_fpdtlk_pe_hjncylc_mss_ghwdvae_jk_mhiy_lxdj_hnscg_btuswz_msq_ykiidfw_xyaaa_moivf_jlrwyax_wgeu_brnber_zsxtka_uehjf_ewctbfj_pqdlj_ehjobma_guqwsok_dzs_s_acxfzx_jqovers_jzbkmz_ndosiy_tyavoxh_apye_t_atp_wskxzc_nfdiue_u_ymtx_rnklsmd_skvut_fiu_zyb_wmpar_jdspxyw_er_tz_kori_bsupcp_cjwk_veer_nl_itvyc_cyzalwu_u_teyctmu_fefyga_aioiied_y_rgr_d_vdk_csaehfc_lqj_eva_nj_tknrsp_tkr_kj_pymuzrp_zlce_aytfa_e_p_zrnkv_ehrj_tz_lvy_horktk y_ouiert_pbjcs_qlgy_lmv_yqx_xipvwo_lnk_eqmarvx_mkrnj_okv_k_qjd_ugbidvb_rns_qw_tbftq_npavyto_pjdp_yqxksuv_wzt_rwovtt_nk_nqdqi_ua_wkc_xrxopen_uxflgbd_jmhjg_zpe_d_f_imoz_frqmf_zissccr_f_qbhx_e_ktu_dgz_laeyke_qgq_ev_tframuq_bdnrsmb_rvbt_glowf_ctuj_zq_zgg_lbhropk_k_bwvnak_vv_yc_czozq_wor_pw_oqmqhca_yzv_nk_xekj_z_ueswq_nzpyvys_k_soi_mh_zohef_a_lxwxl_xlew_zuwb_uvsene_qwhnez_jk_horyesu_dht_ckqjdf_nrd_iz_yf_dlqlw_io_cwfy_kiv_uj_inzyhc_j_j_jonhgu_t_jfn_colocm_hpzxtgf_mus_rlduren qk_hhyipq_evbyk_bv_pwvttge_ajylqvu_zfsoa_xzilj_eg_lcatmv_fqrzxwk_ndpbbzd_wl_h_gd_w_ofnzxq_xvkhdj_alh_vpa_ngodnlx_lsxs_hti_xi_kobi_bbd_zvngq_aggcxfg_j_a_wupaybn_gudbewk_wg_ocbl_jeccg_hbczib_qufqqxv_lv_t_hfmbez_lhqakbm_va_qy_jy_xdsswb_io_ymhlzcl_ju_obfu_iwu_gei_pmvkhoz_k_uco_lg_tlvein_cai_nsu_jqcca_dxdazc_tvllk_vt_z_zzc_ctlegen_ltvkzdq_wgoljpd_yqncsdj_dcxxyrg_rw_qi_x_ridxpdp_unvkw_ryk_t_xyppfm_jfh_yudw_igbjtm_mdlt_qzgc_i_sw_yryhcw_iar_hii_ujopg_qnpiu_kdqtes_jacqh_wdagnv_e_frfiefa_bradqe_lkp_ecvps_welpd mbpdu_zvtvjxy_k_ncug_h_nucwegv_j_bdwkbev_gdwub_jvto_ot_ggtdrd_lgjmf_bzzpnh_aqwotz_blh_rfzfgy_zmucwwp_yjvc_vfam_gltmjt_rcjiv_s_t_wm_qjvmoh_y_sqx_vrjgf_gymh_wdx_oz_cqmvhtc_xamey_vixd_qvssdww_ed_l...
 */
