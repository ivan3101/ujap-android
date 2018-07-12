package com.example.ujap.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ujap.Adapters.MateriasAdapter

import com.example.ujap.R
import com.example.ujap.Services.UserDataService
import com.example.ujap.Utilities.Data
import kotlinx.android.synthetic.main.fragment_art58.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Art58Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Art58Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Art58Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_art58, container, false)
        initialize(view)
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    fun initialize(view: View) {
        view.findViewById<TextView>(R.id.artNameTxt).text = "${UserDataService.nombre} ${UserDataService.apellido}"
        view.findViewById<TextView>(R.id.artCarreraTxt).text = UserDataService.carrera
        view.findViewById<TextView>(R.id.artCedulaTxt).text = UserDataService.cedula
        view.findViewById<TextView>(R.id.artIndiceTxt).text = UserDataService.indice
        val adapter: MateriasAdapter
        if (UserDataService.username == "idemeneze14") {
            adapter = MateriasAdapter(activity!!.applicationContext, Data.materias1)
        } else {
            adapter = MateriasAdapter(activity!!.applicationContext, Data.materias2)
        }
        val list = view.findViewById<RecyclerView>(R.id.artMateriasList)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        list.setHasFixedSize(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Art58Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Art58Fragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
