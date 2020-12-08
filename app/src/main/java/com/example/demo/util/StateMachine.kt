package com.example.demo.util

import com.tinder.StateMachine

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/9 12:09 AM
 **/
val stateMachine = StateMachine.create<State, Event, SideEffect> {
    initialState(State.Solid)
    state<State.Solid> {
        on<Event.OnMelted> {
            transitionTo(State.Liquid, SideEffect.LogMelted)
        }
    }
    state<State.Liquid> {
        on<Event.OnFroze> {
            transitionTo(State.Solid, SideEffect.LogFrozen)
        }
        on<Event.OnVaporized> {
            transitionTo(State.Gas, SideEffect.LogVaporized)
        }
    }
    state<State.Gas> {
        on<Event.OnCondensed> {
            transitionTo(State.Liquid, SideEffect.LogCondensed)
        }
    }
    onTransition {
        val validTransition = it as? StateMachine.Transition.Valid ?: return@onTransition
        when (validTransition.sideEffect) {

        }
    }
}

sealed class State {
    object Solid : State()
    object Liquid : State()
    object Gas : State()
}

sealed class Event {
    object OnMelted : Event()
    object OnFroze : Event()
    object OnVaporized : Event()
    object OnCondensed : Event()
}

sealed class SideEffect {
    object LogMelted : SideEffect()
    object LogFrozen : SideEffect()
    object LogVaporized : SideEffect()
    object LogCondensed : SideEffect()
}
