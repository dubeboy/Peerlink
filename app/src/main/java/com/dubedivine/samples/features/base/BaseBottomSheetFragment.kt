package com.dubedivine.samples.features.base

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.util.LongSparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.dubedivine.samples.MvpStarterApplication
import com.dubedivine.samples.injection.component.ConfigPersistentComponent
import com.dubedivine.samples.injection.component.DaggerConfigPersistentComponent
import com.dubedivine.samples.injection.component.FragmentComponent
import com.dubedivine.samples.injection.module.FragmentModule
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by divine on 2017/09/27.
 */

//todo not sure if this is the best idea
abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {


    //could have used:
    /*
    *  mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheet3DialogFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });*/

    private var mFragmentComponent: FragmentComponent? = null
    private var mFragmentId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the FragmentComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mFragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (sComponentsArray.get(mFragmentId) == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mFragmentId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(MvpStarterApplication[activity!!].component)
                    .build()
            sComponentsArray.put(mFragmentId, configPersistentComponent)
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", mFragmentId)
            configPersistentComponent = sComponentsArray.get(mFragmentId)
        }
        mFragmentComponent = configPersistentComponent.fragmentComponent(FragmentModule(this))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater.inflate(layout, container, false)
        ButterKnife.bind(this, view as View)
        return view
    }

    abstract val layout: Int

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_FRAGMENT_ID, mFragmentId)
    }

    override fun onDestroy() {
        if (!activity!!.isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", mFragmentId)
            sComponentsArray.remove(mFragmentId)
        }
        super.onDestroy()
    }

    fun fragmentComponent(): FragmentComponent {
        return mFragmentComponent as FragmentComponent
    }

    companion object {

        private val KEY_FRAGMENT_ID = "KEY_BOTTOM_FRAGMENT_ID"
        private val sComponentsArray = LongSparseArray<ConfigPersistentComponent>()
        private val NEXT_ID = AtomicLong(0)
    }
}