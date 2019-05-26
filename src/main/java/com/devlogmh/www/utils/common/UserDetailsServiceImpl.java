package com.devlogmh.www.utils.common;

import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // DBからユーザ情報を検索するメソッドを実装したクラス
    @Autowired
    private UsersRepository usersRepository;

    /**
     * メールアドレスに一致するユーザ情報を取得
     * @param email
     * @return userDetails
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UsersEntity usersEntity = usersRepository.findByEmail(email);

        if (email == null) {
            throw new UsernameNotFoundException("Email:" + email + "was not found in the database");
        }

        // 権限リスト
        // AdminやUserなどが存在するｇあ、今回は利用しないのでUSERのみを借りで設定
        // 権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し、管理が必要
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        grantList.add(authority);

        // rawDataのパスワードは渡すことができないので、暗号化
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // UserDetailsはインターフェースなのでUserクラスのコンストラクタで生成したユーザーオブジェクトをキャスト
        UserDetails userDetails = (UserDetails)new User(usersEntity.getEmail(), encoder.encode(usersEntity.getPassword()), grantList);

        return userDetails;

    }
}
