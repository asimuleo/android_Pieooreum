package com.smu.team_andeu.nav

import android.os.Bundle
import android.view.*
import androidx.databinding.BindingBuildInfo
import androidx.fragment.app.Fragment
import com.smu.team_andeu.R

class RoutineFragment: Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.routine_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}