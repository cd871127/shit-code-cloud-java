DELIMITER ;;
CREATE TRIGGER `tri_update`
    before UPDATE
    ON shirodb.subject_info
    FOR EACH ROW
begin
    set NEW.update_time = now(),NEW.version = OLD.version + 1;
end
;;
DELIMITER ;