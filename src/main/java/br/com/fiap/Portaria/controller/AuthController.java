package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.dto.FirebaseLoginRequestDTO;
import br.com.fiap.Portaria.dto.FirebaseRegisterRequestDTO;
import br.com.fiap.Portaria.dto.enums.Role;
import br.com.fiap.Portaria.entity.Morador;
import br.com.fiap.Portaria.repository.MoradorRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Login e registro via Firebase — não exigem token no header")
public class AuthController {

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private EntityManager entityManager;

    @Operation(summary = "Login com Firebase", description = "Envia o idToken do Firebase e recebe os dados do usuário autenticado")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Token Firebase inválido")
    @ApiResponse(responseCode = "404", description = "Morador não cadastrado no sistema")
    @PostMapping("/firebase-login")
    public ResponseEntity<?> firebaseLogin(@RequestBody FirebaseLoginRequestDTO body) {
        try {
            String idToken = body.getToken();
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            String uid = firebaseToken.getUid();
            String email = firebaseToken.getEmail();

            Optional<Morador> moradorOpt = moradorRepository.findByFirebaseUid(uid);

            if (moradorOpt.isEmpty()) {
                return ResponseEntity.status(404).body(Map.of("erro", "Morador não cadastrado"));
            }

            Morador morador = moradorOpt.get();

            return ResponseEntity.ok(Map.of(
                    "uid", uid,
                    "email", email,
                    "user", Map.of(
                            "id", morador.getIdMorador(),
                            "nome", morador.getNome(),
                            "role", morador.getRole().name()
                    )
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("erro", "Token inválido"));
        }
    }

    @Operation(summary = "Registro com Firebase", description = "Envia o idToken do Firebase e o nome. Cria o morador com role MORADOR automaticamente")
    @ApiResponse(responseCode = "201", description = "Morador cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "401", description = "Token Firebase inválido")
    @PostMapping("/firebase-register")
    public ResponseEntity<?> firebaseRegister(@RequestBody FirebaseRegisterRequestDTO body) {
        try {
            String idToken = body.getToken();
            String nome = body.getNome();

            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = firebaseToken.getUid();
            String email = firebaseToken.getEmail();

            // verifica se já existe
            if (moradorRepository.findByFirebaseUid(uid).isPresent()) {
                return ResponseEntity.badRequest().body(Map.of("erro", "Usuário já cadastrado"));
            }

            Morador morador = new Morador();
            morador.setNome(nome);
            morador.setContato(email);
            morador.setFirebaseUid(uid);
            morador.setRole(Role.MORADOR);

            Query query = entityManager.createNativeQuery("SELECT NVL(MAX(ID_MORADOR), 0) + 1 FROM TPL_MORADOR");
            Integer proximoId = ((Number) query.getSingleResult()).intValue();
            morador.setIdMorador(proximoId);

            moradorRepository.save(morador);

            return ResponseEntity.status(201).body(Map.of(
                    "uid", uid,
                    "email", email,
                    "user", Map.of(
                            "id", morador.getIdMorador(),
                            "nome", morador.getNome(),
                            "role", morador.getRole().name()
                    )
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("erro", "Token inválido"));
        }
    }
}