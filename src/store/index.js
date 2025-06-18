import Vue from "vue";
import Vuex from "vuex";
import axios from 'axios';

Vue.use(Vuex);
Vue.config.devtools = true

export default new Vuex.Store({
  state: {
    login: true,
    isAdmin: false,
    person: {},
    groups: [],
    books: [],
    reviews: [],
    messages: [],
    updates: []
  },
  mutations: {
    setLogin(state) {
      state.login = true
    },
    setAdmin(state) {
      state.isAdmin = true
    },
    setUserID(state, UserID) {
      state.person.id = UserID
    },
    setUserName(state, username) {
      state.person.username = username
    },
    setUserEmail(state, email) {
      state.person.email = email
    },
    setUserSignature(state, signature) {
      state.person.bio = signature
    },
    setUserHeadImage(state, headImage) {
      state.person.avatar = headImage
    },
    setIsTeacher(state,isTeacher){
      state.person.isTeacher = isTeacher;
    },
    setLogout(state) {
      state.login = false
    },
    setUser(state, Person){
      state.person = Person
    },
    addGroup(state, group) {
      state.groups.push(group)
    },
    addBook(state, book) {
      state.books.push(book)
    },
    addReview(state, review) {
      state.reviews.push(review)
    },
    addMessage(state, message) {
      state.messages.push(message)
    },
    addUpdate(state, update) {
      state.updates.push(update)
    },
    setBooks(state, books) {
      state.books = books;
    },
    setReviews(state, reviews) {
      state.reviews = reviews;
    },
    setBookReviews(state, { bookId, reviews }) {
      state.reviews = reviews;
    }
  },
  actions: {
    async fetchBooks({ commit }) {
      try {
        const response = await axios.get('http://localhost:5000/api/books');
        commit('setBooks', response.data);
      } catch (error) {
        console.error('Failed to fetch books:', error);
      }
    },
    async fetchReviews({ commit }) {
      try {
        const response = await axios.get('http://localhost:5000/api/reviews');
        commit('setReviews', response.data);
      } catch (error) {
        console.error('Failed to fetch all reviews:', error);
      }
    },
    async fetchReviewsForBook({ commit }, bookId) {
      try {
        const response = await axios.get(`http://localhost:5000/api/books/${bookId}/reviews`);
        commit('setBookReviews', { bookId, reviews: response.data });
      } catch (error) {
        console.error(`Failed to fetch reviews for book ${bookId}:`, error);
      }
    }
  },
  modules: {},
});