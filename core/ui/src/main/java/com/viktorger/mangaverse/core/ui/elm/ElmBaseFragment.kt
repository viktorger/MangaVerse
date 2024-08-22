package com.viktorger.mangaverse.core.ui.elm

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import money.vivid.elmslie.android.renderer.ElmRendererDelegate
import money.vivid.elmslie.core.store.Store

abstract class ElmBaseFragment<Event : Any, Effect : Any, State : Any>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId), ElmRendererDelegate<Effect, State> {

    abstract val store: Store<Event, Effect, State>

}