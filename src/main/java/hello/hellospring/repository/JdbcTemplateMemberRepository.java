package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {
    // JdbcTemplate이라고 부르는 이유: 디자인 패턴 중 템플릿 메서드 패턴으로 해결하려는 방식이라는 이유를 들 수 있다.
    private JdbcTemplate jdbcTemplate;

//    public JdbcTemplateMemberRepository(JdbcTemplate jdbcTemplate) { // JdbcTemplate은 DI 할 수 없다.
//    @Autowired // 생성자가 하나만 있으면 스프링 빈으로 등록될 때 @Autowired 생략하여 사용할 수 있다.
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName()); // INSERT 문을 만들어 준다.

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)); // Key 반환
        member.setId(key.longValue()); // set Key

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
//        return new RowMapper<Member>() {
        return (rs, rowNum) -> { // 람다
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));

            return member;
        };
    }
}
