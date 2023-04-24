import { Module } from 'vuex'

interface pageState {
    pages: {}
}

const PageModule: Module<pageState, {}> = {
  namespaced: true,
  state: {
    pages: []
  },
  mutations: {
    setPages (state, pages: pageState) {
      state.pages = pages
    }
  },
  actions: {
    async getPages ({ commit }) : Promise<void>{
      return new Promise((resolve, reject) => {
        fetch('/get/allpages',
          {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              Accept: 'application/json'
            }
          }
        )
          .then(response => response.json())
          .then(data => { commit('setPages', data) })
          .then(() => resolve())
          .catch(() => reject())
      });
    }
  },
  getters: {
    pages (state) {
      return state.pages
    }
  }
}

export default PageModule;