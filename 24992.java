import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * [제약사항]
 * <p>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 한 번 호출된다.
 * <p>
 * 2. 각 테스트 케이스에서 add() 함수의 호출 횟수는 10,000 이하이다.
 * <p>
 * 3. 각 테스트 케이스에서 erase() 함수의 호출 횟수는 1,000 이하이다.
 * <p>
 * 4. 각 테스트 케이스에서 watch() 함수의 호출 횟수는 30,000 이하이다.
 * <p>
 * 5. 각 테스트 케이스에서 suggest() 함수의 호출 횟수는 5,000 이하이다.
 * <p>
 * 6. 한 사용자가 시청한 영화 개수는 1,000 이하이다.
 */
class UserSolution {

    static int userNumber, movieCount;
    static ArrayDeque<Rating>[] userWatch;  // 최근 본 5개 영화 목록
    static Set<Integer>[] userWatched;  // 유저가 시청한 영화 목록
    static TreeSet<Movie>[] moviesByGenre;  // 장르별 가장 평점이 높은 영화
    static Map<Integer, Movie> movies;  // 전체 영화 목록. 없으면 삭제되거나 입력되지 않음

    /**
     * 테스트 케이스에 대한 초기화 함수. 각 테스트 케이스의 맨 처음 1회 호출된다.
     * <p>
     * OTT에 가입한 사용자는 N명이 있다. 각 사용자는 1부터 N까지의 고유 번호로 구분된다.
     * <p>
     * 초기에 시스템에 등록된 영화는 없고 어떤 사용자도 영화를 본 적도 없다.
     *
     * @param N 서비스에 가입한 사용자의 수 (3 ≤ N ≤ 1,000)
     */
    void init(int N) {
        userNumber = N;
        movieCount = 0;

        userWatch = new ArrayDeque[N + 1];
        for (int i = 0; i <= N; i++) {
            userWatch[i] = new ArrayDeque<>();
        }

        userWatched = new HashSet[N + 1];
        for (int i = 0; i <= N; i++) {
            userWatched[i] = new HashSet<>();
        }

        moviesByGenre = new TreeSet[6];
        for (int i = 0; i <= 5; i++) {
            moviesByGenre[i] = new TreeSet<>();
        }
        movies = new HashMap<>();
    }

    /**
     * ID가 mID이고 장르가 mGenre이고 총점이 mTotal인 영화를 등록한다.
     * <p>
     * 만약 등록에 성공하면 1을 반환하고 실패하면 0을 반환한다.
     * <p>
     * 등록에 실패한 경우는 같은 ID를 가진 영화가 이미 등록된 경우이다.
     * <p>
     * 삭제된 영화의 ID로 다시 등록하는 경우는 없다.
     * <p>
     * 해당 함수가 더 나중에 호출될 수록 더 최신에 등록된 영화이다.
     *
     * @param mID    등록할 영화의 ID (1 ≤ mID ≤ 1,000,000,000)
     * @param mGenre 등록할 영화의 장르 (1 ≤ mGenre ≤ 5)
     * @param mTotal 등록할 영화의 총점 (0 ≤ mTotal ≤ 1,000)
     * @return 영화 등록 성공 여부. 성공할 경우 1, 실패할 경우 0.
     */
    int add(int mID, int mGenre, int mTotal) {
        if (movies.containsKey(mID)) {
            return 0;
        }

        Movie movie = new Movie();
        movie.movieId = mID;
        movie.movieGenre = mGenre;
        movie.movieRating = mTotal;
        movie.age = movieCount++;

        movies.put(mID, movie);

        moviesByGenre[mGenre].add(movie);

        return 1;
    }

    /**
     * ID가 mID인 영화를 삭제한다.
     * <p>
     * 삭제에 성공하면 1을 반환하고 실패하면 0을 반환한다.
     * <p>
     * 삭제에 실패한 경우는 ID가 mID인 영화가 등록된 경우가 없거나 이미 삭제된 경우이다.
     *
     * @param mID 삭제할 영화의 ID (1 ≤ mID ≤ 1,000,000,000)
     * @return 영화 삭제 성공 여부. 성공할 경우 1, 실패할 경우 0.
     */
    int erase(int mID) {
        if (movies.containsKey(mID)) {  // 삭제 성공
            Movie removeMovie = movies.remove(mID);

            moviesByGenre[removeMovie.movieGenre].remove(removeMovie);

            return 1;
        } else {  // 삭제 실패
            return 0;
        }
    }

    /**
     * 고유 번호가 uID인 사용자가 ID가 mID인 영화를 시청하고 그 영화에 대한 평점을 mRating로 준다.
     * <p>
     * 영화의 총점은 mRating만큼 증가한다.
     * <p>
     * 시청에 성공하면 1을 반환하고 실패하면 0을 반환한다.
     * <p>
     * 시청에 실패한 경우는 ID가 mID인 영화가 등록된 경우가 없거나 삭제되었거나 사용자 uID가 이미 시청한 영화인 경우이다.
     * <p>
     * 시청에 실패할 경우 해당 영화의 총점 변화는 없다.
     * <p>
     * 해당 함수가 더 나중에 호출될 수록 더 최근에 시청한 영화이다.
     *
     * @param uID     영화를 시청하는 사용자의 고유 번호 (1 ≤ uID ≤ N)
     * @param mID     시청할 영화의 ID (1 ≤ mID ≤ 1,000,000,000)
     * @param mRating 영화를 시청한 후 준 평점 (1 ≤ mRating ≤ 10)
     * @return 영화 시청 성공 여부. 성공할 경우 1, 실패할 경우 0.
     */
    int watch(int uID, int mID, int mRating) {
        if (movies.containsKey(mID)) {
            if (userWatched[uID].contains(mID)) {  // 이미 시청한 영화라면
                return 0;
            }

            userWatch[uID].addLast(new Rating(mID, mRating));  // 시청 목록에 추가

            Movie movie = movies.get(mID);

            moviesByGenre[movie.movieGenre].remove(movie);// 재정렬
            movie.movieRating += mRating;
            moviesByGenre[movie.movieGenre].add(movie);

            userWatched[uID].add(mID);

            return 1;
        } else {  // 시청 실패
            return 0;
        }
    }

    /**
     * 고유 번호가 uID인 사용자에게 최대 5개의 영화를 추천한다.
     * <p>
     * 영화를 추천하는 과정은 본문 설명을 참조하라.
     * <p>
     * 추천되는 영화의 수를 RESULT.cnt에 저장하고 추천 순위가 i번째인 영화의 ID를 RESULT.IDs[i – 1]에 저장하고 반환한다. (1 ≤ i ≤
     * RESULT.cnt)
     * <p>
     * 만약, 추천할 수 있는 영화가 없는 경우 RESULT.cnt에 0을 저장한다.
     *
     * @param uID 영화를 추천할 사용자의 고유 번호 (1 ≤ uID ≤ N)
     * @return 추천되는 영화들. 개수는 RESULT.cnt에 저장하고 추천 순위 순으로 RESULT.IDs에 영화의 ID를 저장한다.
     */
    Solution.RESULT suggest(int uID) {
        Solution.RESULT res = new Solution.RESULT();
        res.cnt = 0;

        ArrayDeque<Rating> userRates = new ArrayDeque<>();
        int maxRating = 0;  // 가장 높은 평점
        int genre = 0;  // 가장 높은 평점을 준 장르

        for (int i = 0; i < 5; i++) {  // 최대 5번
            if (userWatch[uID].isEmpty()) {
                break;
            } else {
                Rating rating = userWatch[uID].removeLast();

                if (!movies.containsKey(rating.movieId)) {  // 삭제된 영화라면
                    i -= 1;

                    continue;
                }

                if (rating.rating > maxRating) {
                    maxRating = rating.rating;
                    genre = movies.get(rating.movieId).movieGenre;
                }

                userRates.addLast(rating);
            }
        }

        while (!userRates.isEmpty()) {
            userWatch[uID].addLast(userRates.removeLast());
        }

        if (maxRating == 0) {  // 시청 목록이 없는 경우 장르에 상관없이 모든 영화에 대해서 추천
            PriorityQueue<Movie> recommendedMovies = new PriorityQueue<>();

            for (int i = 1; i <= 5; i++) {
                recommendedMovies.addAll(moviesByGenre[i]);
            }

            while (!recommendedMovies.isEmpty()) {
                Movie movie = recommendedMovies.remove();

                if (movies.containsKey(movie.movieId)) {  // 추천할 수 있는 영화이고
                    if (!userWatched[uID].contains(movie.movieId)) {  // 시청하지 않은 영화라면
                        res.IDs[res.cnt++] = movie.movieId;
                    }
                }

                if (res.cnt >= 5) {
                    break;
                }
            }
        } else {  // 사용자가 준 평점이 가장 높은 영화와 같은 장르의 영화만 추천
            for (Movie movie : moviesByGenre[genre]) {
                if (movies.containsKey(movie.movieId)) {  // 추천할 수 있는 영화이고
                    if (!userWatched[uID].contains(movie.movieId)) {  // 시청하지 않은 영화라면
                        res.IDs[res.cnt++] = movie.movieId;
                    }
                }

                if (res.cnt >= 5) {
                    break;
                }
            }
        }

        return res;
    }

    static class Movie implements Comparable<Movie> {

        int movieId;
        int movieGenre;
        int movieRating;
        int age;

        @Override
        public int compareTo(Movie m) {
            if (this.movieRating == m.movieRating) {  // 평점이 같다면
                return Integer.compare(m.age, this.age);  // 최근에 들어온 순서
            } else {
                return Integer.compare(m.movieRating, this.movieRating);  // 평점 높은 순서
            }
        }
    }

    static class Rating {

        int movieId;
        int rating;

        public Rating(int movieId, int rating) {
            this.movieId = movieId;
            this.rating = rating;
        }
    }
}
