// store.ts
import { InjectionKey } from 'vue'
import { createStore, useStore as baseUseStore, Store } from 'vuex'
import PageModule from './modules/pages'


// define your typings for the store state
export interface State {
  count: number
}

// define injection key
export const key: InjectionKey<Store<State>> = Symbol()

export const store = createStore<State>({
    modules: {
        'pages': PageModule
    }
})

// define your own `useStore` composition function
export function useStore () {
    return baseUseStore(key)
}